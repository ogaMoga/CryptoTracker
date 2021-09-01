package com.example.cryptotracker.data.database.dao

import androidx.room.*
import com.example.cryptotracker.data.database.model.CoinsEntity
import com.example.cryptotracker.data.database.model.FavouritesEntity
import com.example.cryptotracker.domain.model.Coin

@Dao
interface ListDao {
    @Query("SELECT * FROM coins NATURAL JOIN favourites")
    suspend fun getCoinsList(): List<Coin>

    @Query("SELECT name FROM coins")
    suspend fun getNames(): List<String>

    @Update
    suspend fun updateFavourite(favouritesEntity: FavouritesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCoinList(coins: List<CoinsEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setFavouriteList(list: List<FavouritesEntity>)
}
