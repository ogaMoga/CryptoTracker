package com.example.cryptotracker.di

import android.content.Context
import com.example.cryptotracker.screens.MainActivity
import com.example.cryptotracker.screens.card.CardFragment
import com.example.cryptotracker.screens.search.SearchFragment
import com.example.cryptotracker.screens.start.StartFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules =
    [DatabaseModule::class,
    NetworkModule::class,
    ScreenModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(startFragment: StartFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(cardFragment: CardFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}