package com.example.cryptotracker.domain.model

data class Coin(
    val name: String,
    val description: String,
    val logo: Int,
    val price: Double,
    val diff: Double,
    var isFavourite: Boolean
)
