package com.example.cryptotracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptotracker.data.database.model.DetailsEntity
import com.example.cryptotracker.domain.model.CardData

@Dao
interface CardDao {
    @Query("SELECT * FROM details NATURAL JOIN favourites WHERE name = :name")
    suspend fun getCoinDetails(name: String): CardData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCoinDetails(data: List<DetailsEntity>)
}
