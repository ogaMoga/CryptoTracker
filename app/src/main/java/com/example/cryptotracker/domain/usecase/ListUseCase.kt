package com.example.cryptotracker.domain.usecase

import com.example.cryptotracker.data.repository.ListLocalRepository
import com.example.cryptotracker.data.repository.ListRemoteRepository
import com.example.cryptotracker.domain.model.Coin
import com.example.cryptotracker.utils.CoinListResource
import com.example.cryptotracker.utils.Status

class ListUseCase(
    private val localRepository: ListLocalRepository,
    private val remoteRepository: ListRemoteRepository,
) {
    private val data = listOf(
        Coin("BTC", "Bitcoin", 500.0, 1.0, true),
        Coin("ETH", "Ethereum", 100.0, -666.0, false),
        Coin("DOGE", "Dogecoin", 9999.0, 2002.0, true),
        Coin("PIT", "PopItCoin", 300.0, 13.0, false),
        Coin("ADA", "Cardano", 2.78, -4.25, false),
        Coin("BNB", "Binance Coin", 465.02, -6.94, false),
        Coin("USDT", "Tether", 1.00, -0.04, false),
        Coin("XRP", "XRP", 1.11, -2.43, true)
    )

    suspend fun loadItems(): CoinListResource =
        CoinListResource(Status.SUCCESS,  data)

    fun setFavourite(position: Int, isFavourite: Boolean) {
        data[position].isFavourite = isFavourite
    }
}
