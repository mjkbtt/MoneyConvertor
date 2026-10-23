package com.mjkbtt.moneyconvertor

data class Currency(
    val code: String,
    val name: String,
    val flagResId: Int,
    val rateToMnt: Double
)
