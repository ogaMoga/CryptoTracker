package com.example.cryptotracker.domain.usecase

import com.example.cryptotracker.data.repository.ListLocalRepository
import com.example.cryptotracker.data.repository.ListRemoteRepository
import com.example.cryptotracker.domain.model.*
import com.example.cryptotracker.exception.DatabaseException
import com.example.cryptotracker.exception.LostConnectionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListUseCase(
    private val localRepository: ListLocalRepository,
    private val remoteRepository: ListRemoteRepository,
) {
    suspend fun loadCoinList(): CoinListResource = withContext(Dispatchers.IO) {
        try {
            return@withContext CoinListResource(Status.SUCCESS, localRepository.getCoinList())
        } catch (e: DatabaseException) {
            val general: GeneralApiResponse
            try {
                general = remoteRepository.getGeneralInfo()
            } catch (e: LostConnectionException) {
                return@withContext CoinListResource(Status.ERROR, null)
            }
            val result = mutableListOf<Coin>()
            for (coin in general.data) {
                result.add(Coin(
                    name = coin.symbol,
                    description = coin.name,
                    price = coin.quote.usd.price,
                    diff = coin.quote.usd.percent_change_24h,
                    logo = coin.id,
                    isFavourite = false
                ))
            }

            localRepository.addCoinList(result)

            return@withContext CoinListResource(Status.SUCCESS, result)
        }
    }

    suspend fun refreshCoinList(): CoinListResource = withContext(Dispatchers.IO) {
        val remoteRepositoryData = try {
            remoteRepository.getGeneralInfo()
        } catch (e: LostConnectionException) {
            return@withContext CoinListResource(Status.ERROR, null) // quite error
        }
        val result = mutableListOf<Coin>()
        for (coin in remoteRepositoryData.data) {
            result.add(Coin(
                name = coin.symbol,
                description = coin.name,
                price = coin.quote.usd.price,
                diff = coin.quote.usd.percent_change_24h,
                logo = coin.id,
                isFavourite = false
            ))
        }

        localRepository.addCoinList(result)

        return@withContext loadCoinList()
    }

    suspend fun setFavourite(name: String, isFavourite: Boolean) = withContext(Dispatchers.IO) {
        localRepository.updateFavourite(name, isFavourite)
    }
}
