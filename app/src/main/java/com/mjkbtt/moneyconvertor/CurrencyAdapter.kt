package com.mjkbtt.moneyconvertor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter(
    private val currencies: List<Currency>,
    private val onItemClick: (Currency) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>()
{

    class CurrencyViewHolder(itemView: View) : RecyclerView.
    ViewHolder(itemView) {
        val imgFlag: ImageView = itemView.findViewById(R.id.
        imgFlag)
        val tvName: TextView = itemView.findViewById(R.id.
        tvCurrencyName)
        val tvCode: TextView = itemView.findViewById(R.id.
        tvCurrencyCode)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }


    override fun onBindViewHolder(holder: CurrencyViewHolder,
                                  position: Int) {
        val item = currencies[position]
        holder.imgFlag.setImageResource(item.flagResId)
        holder.tvName.text = item.name
        holder.tvCode.text = item.code

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = currencies.size
}