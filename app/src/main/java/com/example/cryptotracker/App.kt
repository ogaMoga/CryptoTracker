package com.example.cryptotracker

import android.app.Application
import com.example.cryptotracker.di.core.databaseModule
import com.example.cryptotracker.di.core.networkModule
import com.example.cryptotracker.di.core.repositoryModule
import com.example.cryptotracker.di.screenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    companion object {
        const val ITEM_STAR_KEY = "item_star"
        const val ITEM_ID_KEY = "item_id"
        const val COIN_LIST_KEY = "coin_list"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                screenModule
            )
        }
    }
}