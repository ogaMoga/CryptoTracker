package com.example.cryptotracker.di

import com.example.cryptotracker.data.api.ApiFactory
import com.example.cryptotracker.data.api.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }
}