package com.example.cryptotracker.domain.model

data class Coin(val name: String, val desc: String, var price: Double, var priceDiff: Double, var isFavourite: Boolean )
