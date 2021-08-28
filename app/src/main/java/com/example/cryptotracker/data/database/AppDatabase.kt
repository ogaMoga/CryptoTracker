package com.example.cryptotracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptotracker.data.database.dao.CardDao
import com.example.cryptotracker.data.database.dao.ListDao
import com.example.cryptotracker.data.database.dao.SearchDao
import com.example.cryptotracker.data.database.model.TestEntity

@Database(entities = [TestEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val cardDao: CardDao
    abstract val listDao: ListDao
    abstract val searchDao: SearchDao
}
