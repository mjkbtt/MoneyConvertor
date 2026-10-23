package com.mjkbtt.moneyconvertor

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Камер болон status bar-ны доогуур орохоос сэргийлж зай авах
        val mainLayout: View = findViewById(R.id.mainLayout)
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvCurrencies: RecyclerView = findViewById(R.id.rvCurrencies)
        rvCurrencies.layoutManager = LinearLayoutManager(this)

        val currencyList = listOf(
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

        // Валют дээр дарахад ConvertActivity рүү мэдээллийг дамжуулан үсэрнэ
        val adapter = CurrencyAdapter(currencyList) {
                selectedCurrency ->
            val intent = Intent(this, ConvertActivity::class.java).apply {
                putExtra("SELECTED_CODE", selectedCurrency.code)
            }
            startActivity(intent)
        }

        rvCurrencies.adapter = adapter
    }
}