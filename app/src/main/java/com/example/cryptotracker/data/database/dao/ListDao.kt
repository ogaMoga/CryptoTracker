package com.example.cryptotracker.data.database.dao

import androidx.room.*
import com.example.cryptotracker.data.database.model.CoinsEntity
import com.example.cryptotracker.data.database.model.FavouritesEntity
import com.example.cryptotracker.domain.model.Coin

@Dao
interface ListDao {
    @Query("SELECT * FROM coins NATURAL JOIN favourites")
    suspend fun getCoinsList(): List<Coin>

    @Update
    suspend fun updateFavourite(favouritesEntity: FavouritesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCoinList(coins: List<CoinsEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun setFavouriteList(list: List<FavouritesEntity>)
}
