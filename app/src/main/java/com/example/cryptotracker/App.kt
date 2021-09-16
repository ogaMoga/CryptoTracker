package com.example.cryptotracker

import android.app.Application
import android.content.Context
import com.example.cryptotracker.di.AppComponent
import com.example.cryptotracker.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }

    companion object {
        const val ITEM_ID_KEY = "item_id"
        const val COIN_LIST_KEY = "coin_list"
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }