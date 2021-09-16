package com.example.cryptotracker.data.repository

import com.example.cryptotracker.data.database.dao.CardDao
import com.example.cryptotracker.data.database.model.DetailsEntity
import com.example.cryptotracker.domain.model.CardData
import com.example.cryptotracker.exception.DatabaseException
import javax.inject.Inject

class CardLocalRepository @Inject constructor(
    private val cardDao: CardDao
) {
    suspend fun getCoinDetails(name: String): CardData {
        val entity = cardDao.getCoinDetails(name)

        if (entity != null) {
            return entity
        } else {
            throw DatabaseException()
        }
    }

    suspend fun addCoinDetails(data: List<CardData>) = try {
        val result = mutableListOf<DetailsEntity>()
        for (coin in data) {
            result.add(DetailsEntity(
                name = coin.name,
                description = coin.description,
                rank = coin.rank,
                price = coin.price,
                volume24h = coin.volume24h,
                marketCap = coin.marketCap,
                percentChange1h = coin.percentChange1h,
                percentChange24h = coin.percentChange24h,
                percentChange7d = coin.percentChange7d,
                percentChange30d = coin.percentChange30d,
                percentChange60d = coin.percentChange60d,
                percentChange90d = coin.percentChange90d
            ))
        }
        cardDao.setCoinDetails(result)
        true
    } catch (e: Exception) {
        false
    }
}
