package com.example.cryptotracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptotracker.data.database.model.SearchEntity

@Dao
interface SearchDao {
    @Query("SELECT name FROM search WHERE time IS NOT null ORDER BY time DESC LIMIT 5")
    suspend fun getHistory(): List<String>

    @Query("SELECT name FROM search WHERE isPopular = 1")
    suspend fun getPopular(): List<String>

    @Query("SELECT * FROM search WHERE name IN (:names)")
    suspend fun getEntitiesByNames(names: List<String>): List<SearchEntity>

    @Query("SELECT * FROM search WHERE name =:name")
    suspend fun getEntityByName(name: String): SearchEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularList(coins: List<SearchEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRequest(coin: SearchEntity)

}
