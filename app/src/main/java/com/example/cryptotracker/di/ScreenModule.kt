package com.example.cryptotracker.di

import com.example.cryptotracker.domain.usecase.CardUseCase
import com.example.cryptotracker.domain.usecase.ListUseCase
import com.example.cryptotracker.domain.usecase.SearchUseCase
import com.example.cryptotracker.screens.card.CardViewModel
import com.example.cryptotracker.screens.common.ScreenNavigator
import com.example.cryptotracker.screens.search.SearchViewModel
import com.example.cryptotracker.screens.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val screenModule = module {
    single { ScreenNavigator() }

    single { CardUseCase(get(), get()) }
    viewModel { CardViewModel(get(), get()) }

    single { ListUseCase(get(), get()) }
    viewModel { StartViewModel(get(), get()) }

    single { SearchUseCase(get()) }
    viewModel { SearchViewModel(get(), get()) }
}