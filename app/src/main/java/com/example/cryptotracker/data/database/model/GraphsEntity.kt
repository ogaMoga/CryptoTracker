package com.example.cryptotracker.data.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "graphs",
    foreignKeys = [ForeignKey(
        entity = CoinsEntity::class,
        parentColumns = ["name"],
        childColumns = ["name"]
    )])
data class GraphsEntity(
    @PrimaryKey val name: String,
    val type: Int,
    val price: Double,
    val time: Long
)
