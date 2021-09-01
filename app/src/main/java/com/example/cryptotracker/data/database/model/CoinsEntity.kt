package com.example.cryptotracker.data.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinsEntity(
    @PrimaryKey val name: String,
    val logo: Int,
    val description: String,
    val price: Double,
    val diff: Double
)
