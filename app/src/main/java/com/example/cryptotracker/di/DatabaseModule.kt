package com.example.cryptotracker.di

import android.content.Context
import androidx.room.Room
import com.example.cryptotracker.data.database.AppDatabase
import com.example.cryptotracker.data.database.dao.CardDao
import com.example.cryptotracker.data.database.dao.ListDao
import com.example.cryptotracker.data.database.dao.SearchDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "user_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideCardDao(database: AppDatabase): CardDao {
        return database.cardDao
    }

    @Provides
    @Singleton
    fun provideListDao(database: AppDatabase): ListDao {
        return database.listDao
    }

    @Provides
    @Singleton
    fun provideSearchDao(database: AppDatabase): SearchDao {
        return database.searchDao
    }
}