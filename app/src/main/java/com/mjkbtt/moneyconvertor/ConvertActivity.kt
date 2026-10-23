package com.mjkbtt.moneyconvertor

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import java.text.NumberFormat
import java.util.Locale

class ConvertActivity : AppCompatActivity() {


    private val currencyList = listOf(
        Currency("USD", "Америк доллар", R.drawable.flag_us, 3591.0),
        Currency("EUR", "Евро", R.drawable.flag_eu, 3995.0),
        Currency("GBP", "Английн фунт", R.drawable.flag_gb, 4778.0),
        Currency("RUB", "Оросын рубль", R.drawable.flag_ru, 38.10),
        Currency("CNY", "Хятадын юань", R.drawable.flag_cn, 537.20),
        Currency("JPY", "Японы иен", R.drawable.flag_jp, 22.72),
        Currency("KRW", "БНСУ-ын вон", R.drawable.flag_kr, 2.59),
        Currency("AUD", "Австрали доллар", R.drawable.flag_au, 2475.0),
        Currency("CHF", "Швейцарь франк", R.drawable.flag_ch, 4312.0),
        Currency("CAD", "Канад доллар", R.drawable.flag_ca, 2537.0),
        Currency("SGD", "Сингапур доллар", R.drawable.flag_sg, 2779.0),
        Currency("SEK", "Швед крон", R.drawable.flag_se, 343.0),
        Currency("TRY", "Туркийн Лир", R.drawable.flag_tr, 70.0),
        Currency("HKD", "Гонконг доллар", R.drawable.flag_hk, 455.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)

        // Камер болон status bar-ны доогуур орохоос сэргийлэх
        val convertMain: View = findViewById(R.id.convertMain)
        ViewCompat.setOnApplyWindowInsetsListener(convertMain) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgCurrencyFlag: ImageView = findViewById(R.id.imgCurrencyFlag)
        val spnCurrency: Spinner = findViewById(R.id.spnCurrency)
        val etAmount: EditText = findViewById(R.id.etAmount)
        val btnConvert: MaterialButton = findViewById(R.id.btnConvert)
        val tvResult: TextView = findViewById(R.id.tvResult)

        // Spinner-т валютын кодуудыг оруулах (EUR, GBP, RUB...)
        val codes = currencyList.map { it.code }
        val spinnerAdapter = ArrayAdapter(this, R.layout.spinner_item, codes)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCurrency.adapter = spinnerAdapter

        // 1-р дэлгэцээс сонгогдож ирсэн валют
        val selectedCode = intent.getStringExtra("SELECTED_CODE") ?: "EUR"
        val selectedIndex = currencyList.indexOfFirst { it.code == selectedCode }
        if (selectedIndex != -1) {
            spnCurrency.setSelection(selectedIndex)
            imgCurrencyFlag.setImageResource(currencyList[selectedIndex].flagResId)
        }

        // Spinner дээр валют солигдох үед тугийг шинэчлэх
        spnCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val current = currencyList[position]
                imgCurrencyFlag.setImageResource(current.flagResId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // "Хөрвүүлэх" товчлуур дарах үед тооцоолох
        btnConvert.setOnClickListener {
            val inputStr = etAmount.text.toString().trim()
            if (inputStr.isEmpty()) {
                Toast.makeText(this, "Мөнгөн дүнгээ оруулна уу", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = inputStr.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this, "Зөв тоо оруулна уу", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            
            val currentCurrency = currencyList[spnCurrency.selectedItemPosition]
            val totalMnt = amount * currentCurrency.rateToMnt

            // 3 орон тутамд таслалтай форматлах

            val formatter = NumberFormat.getNumberInstance(Locale.US)
            tvResult.text = formatter.format(totalMnt.toLong())
        }
    }
}
