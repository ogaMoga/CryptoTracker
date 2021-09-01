package com.example.cryptotracker.data.repository

import com.example.cryptotracker.data.database.dao.ListDao
import com.example.cryptotracker.data.database.model.CoinsEntity
import com.example.cryptotracker.data.database.model.FavouritesEntity
import com.example.cryptotracker.domain.model.Coin
import com.example.cryptotracker.exception.DatabaseException

class ListLocalRepository(
    private val dao: ListDao
) {
    suspend fun getCoinList() : List<Coin> {
        val resultCoins = dao.getCoinsList()

        if (resultCoins.isNotEmpty()) {
            return resultCoins
        } else {
            throw DatabaseException()
        }
    }

    fun addCoinList(data: List<Coin>): Boolean = try {
        val resultCoins = mutableListOf<CoinsEntity>()
        val resultFavourites = mutableListOf<FavouritesEntity>()
        for (coin in data) {
            resultCoins.add(
                CoinsEntity(
                    name = coin.name,
                    description = coin.description,
                    logo = coin.logo,
                    price = coin.price,
                    diff = coin.diff
                )
            )
            resultFavourites.add(
                FavouritesEntity(
                    name = coin.name,
                    isFavourite = false
                )
            )
        }
        dao.setCoinList(resultCoins)
        dao.setFavouriteList(resultFavourites)
        true
    } catch (e: Exception) {
        false
    }

    suspend fun updateFavourite(name: String, isFavourite: Boolean): Boolean = try {
        val entity = FavouritesEntity(
            name = name,
            isFavourite = isFavourite
        )
        dao.updateFavourite(entity)
        true
    } catch (e: Exception) {
        false
    }
}
