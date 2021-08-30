package com.example.cryptotracker.utils

import com.example.cryptotracker.domain.model.Coin

data class CoinListResource(val status: Status, val data: List<Coin>?)
