package com.example.cryptotracker.domain.usecase

import com.example.cryptotracker.data.repository.CardLocalRepository
import com.example.cryptotracker.data.repository.ListLocalRepository
import com.example.cryptotracker.data.repository.ListRemoteRepository
import com.example.cryptotracker.domain.model.*
import com.example.cryptotracker.exception.DatabaseException
import com.example.cryptotracker.exception.LostConnectionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListUseCase(
    private val listLocalRepository: ListLocalRepository,
    private val cardLocalRepository: CardLocalRepository,
    private val remoteRepository: ListRemoteRepository,
) {
    suspend fun loadCoinList(): CoinListResource = withContext(Dispatchers.IO) {
        try {
            return@withContext CoinListResource(Status.SUCCESS, listLocalRepository.getCoinList())
        } catch (e: DatabaseException) {
            val general: GeneralApiResponse
            try {
                general = remoteRepository.getGeneralInfo()
            } catch (e: LostConnectionException) {
                return@withContext CoinListResource(Status.ERROR, null)
            }
            val resultGeneral = mutableListOf<Coin>()
            val resultDetails = mutableListOf<CardData>()
            for (coin in general.data) {
                resultGeneral.add(Coin(
                    name = coin.symbol,
                    description = coin.name,
                    price = coin.quote.usd.price,
                    diff = coin.quote.usd.percent_change_24h,
                    logo = coin.id,
                    isFavourite = false
                ))
                resultDetails.add(CardData(
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

            listLocalRepository.addCoinList(resultGeneral)
            cardLocalRepository.addCoinDetails(resultDetails)

            return@withContext CoinListResource(Status.SUCCESS, resultGeneral)
        }
    }

    suspend fun refreshCoinList(): CoinListResource = withContext(Dispatchers.IO) {
        val remoteRepositoryData = try {
            remoteRepository.getGeneralInfo()
        } catch (e: LostConnectionException) {
            return@withContext CoinListResource(Status.ERROR, null) // quite error
        }
        val resultGeneral = mutableListOf<Coin>()
        val resultDetails = mutableListOf<CardData>()
        for (coin in remoteRepositoryData.data) {
            resultGeneral.add(Coin(
                name = coin.symbol,
                description = coin.name,
                price = coin.quote.usd.price,
                diff = coin.quote.usd.percent_change_24h,
                logo = coin.id,
                isFavourite = false
            ))
            resultDetails.add(CardData(
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

        listLocalRepository.addCoinList(resultGeneral)
        cardLocalRepository.addCoinDetails(resultDetails)

        return@withContext loadCoinList()
    }

    suspend fun setFavourite(name: String, isFavourite: Boolean) = withContext(Dispatchers.IO) {
        listLocalRepository.updateFavourite(name, isFavourite)
    }
}
