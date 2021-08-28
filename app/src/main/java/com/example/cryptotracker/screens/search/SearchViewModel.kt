package com.example.cryptotracker.screens.search

import androidx.lifecycle.ViewModel
import com.example.cryptotracker.domain.usecase.SearchUseCase
import com.example.cryptotracker.screens.common.ScreenNavigator

class SearchViewModel(
    private val useCase: SearchUseCase,
    private val navigator: ScreenNavigator
) : ViewModel() {
    // TODO: Implement the ViewModel
}