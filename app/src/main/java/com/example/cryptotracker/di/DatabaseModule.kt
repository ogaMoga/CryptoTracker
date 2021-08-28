package com.example.cryptotracker.di.core

import android.content.Context
import androidx.room.Room
import com.example.cryptotracker.data.database.AppDatabase
import com.example.cryptotracker.data.database.dao.CardDao
import com.example.cryptotracker.data.database.dao.ListDao
import com.example.cryptotracker.data.database.dao.SearchDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module(createdAtStart = true) {
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "user_database")
            .build()
    }

    fun provideCardDao(database: AppDatabase): CardDao {
        return database.cardDao
    }

    fun provideListDao(database: AppDatabase): ListDao {
        return database.listDao
    }

    fun provideSearchDao(database: AppDatabase): SearchDao {
        return database.searchDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCardDao(get()) }
    single { provideListDao(get()) }
    single { provideSearchDao(get()) }
}