package com.example.cryptotracker.di

import com.example.cryptotracker.screens.common.ScreenNavigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ScreenModule {
    @Provides
    @Singleton
    fun provideScreenNavigator(): ScreenNavigator {
        return ScreenNavigator()
    }
}