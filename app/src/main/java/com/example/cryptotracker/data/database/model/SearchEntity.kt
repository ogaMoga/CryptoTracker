package com.example.cryptotracker.data.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "search",
    foreignKeys = [ForeignKey(
        entity = CoinsEntity::class,
        parentColumns = ["name"],
        childColumns = ["name"]
    )])
data class SearchEntity(
    @PrimaryKey val name: String,
    val isPopular: Boolean,
    val time: Long?
)
