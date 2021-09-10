package com.example.cryptotracker.di

import com.example.cryptotracker.data.api.ApiFactory
import org.koin.dsl.module

val networkModule = module {
    single { ApiFactory.apiService }
}