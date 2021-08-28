package com.example.cryptotracker.di.core

import com.example.cryptotracker.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single { CardLocalRepository(get())}
    single { CardRemoteRepository(get()) }

    single { ListLocalRepository(get()) }
    single { ListRemoteRepository(get()) }

    single { SearchRepository(get()) }
}