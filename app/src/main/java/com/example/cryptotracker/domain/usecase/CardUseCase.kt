package com.example.cryptotracker.domain.usecase

import com.example.cryptotracker.data.repository.CardLocalRepository
import com.example.cryptotracker.data.repository.ListLocalRepository
import com.example.cryptotracker.data.repository.ListRemoteRepository
import com.example.cryptotracker.domain.model.*
import com.example.cryptotracker.exception.DatabaseException
import com.example.cryptotracker.exception.LostConnectionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardUseCase @Inject constructor(
    private val cardLocalRepository: CardLocalRepository,
    private val listLocalRepository: ListLocalRepository,
    private val listRemoteRepository: ListRemoteRepository
) {
    suspend fun setFavourite(name: String, isFavourite: Boolean) = withContext(Dispatchers.IO) {
        listLocalRepository.updateFavourite(name, isFavourite)
    }

    suspend fun loadCoinDetails(name: String): CoinCardResource = withContext(Dispatchers.IO) {
        try {
            return@withContext CoinCardResource(Status.SUCCESS, cardLocalRepository.getCoinDetails(name))
        } catch (e: DatabaseException) {
            return@withContext CoinCardResource(Status.ERROR, null)
        }
    }

    suspend fun refreshCoinDetails(name: String): CoinCardResource = withContext(Dispatchers.IO) {
        val remoteRepositoryData = try {
            listRemoteRepository.getGeneralInfo()
        } catch (e: LostConnectionException) {
            return@withContext CoinCardResource(Status.ERROR, null) // quite error
        }

        val generalResult = mutableListOf<Coin>()
        val detailResult = mutableListOf<CardData>()
        for (coin in remoteRepositoryData.data) {
            generalResult.add(Coin(
                name = coin.symbol,
                description = coin.name,
                price = coin.quote.usd.price,
                diff = coin.quote.usd.percent_change_24h,
                logo = coin.id,
                isFavourite = false
            ))
            detailResult.add(
                CardData(
                    name = coin.symbol,
                    description = coin.name,
                    price = coin.quote.usd.price,
                    rank = coin.cmc_rank,
                    volume24h = coin.quote.usd.volume_24h,
                    marketCap = coin.quote.usd.market_cap,
                    percentChange1h = coin.quote.usd.percent_change_1h,
                    percentChange24h = coin.quote.usd.percent_change_24h,
                    percentChange7d = coin.quote.usd.percent_change_7d,
                    percentChange30d = coin.quote.usd.percent_change_30d,
                    percentChange60d = coin.quote.usd.percent_change_60d,
                    percentChange90d = coin.quote.usd.percent_change_90d,
                    isFavourite = false
            ))
        }

        listLocalRepository.addCoinList(generalResult)
        cardLocalRepository.addCoinDetails(detailResult)
        return@withContext loadCoinDetails(name)
    }
}
