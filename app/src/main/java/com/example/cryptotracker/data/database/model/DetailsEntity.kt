package com.example.cryptotracker.data.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "details",
    foreignKeys = [ForeignKey(
        entity = CoinsEntity::class,
        parentColumns = ["name"],
        childColumns = ["name"]
    )])
data class DetailsEntity(
    @PrimaryKey val name: String,
    val description: String,
    val rank: Int,
    val price : Double,
    val volume24h: Double,
    val marketCap: Double,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val percentChange7d: Double,
    val percentChange30d: Double,
    val percentChange60d: Double,
    val percentChange90d: Double
)
