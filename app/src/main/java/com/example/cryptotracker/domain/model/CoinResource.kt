package com.example.cryptotracker.domain.model

data class CoinListResource(val status: Status, val data: List<Coin>?)

data class CoinCardResource(
    val status: Status,
    val data: CardData?,
)

data class CardData(
    val name: String,
    val isFavourite: Boolean,
    val description: String,
    val price : Double,
    val rank: Int,
    val volume24h: Double,
    val marketCap: Double,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val percentChange7d: Double,
    val percentChange30d: Double,
    val percentChange60d: Double,
    val percentChange90d: Double
)
