package com.example.cryptotracker.data.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "favourites",
    foreignKeys = [ForeignKey(
    entity = CoinsEntity::class,
    parentColumns = ["name"],
    childColumns = ["name"]
)])
data class FavouritesEntity(
    @PrimaryKey val name: String,
    val isFavourite: Boolean
)
