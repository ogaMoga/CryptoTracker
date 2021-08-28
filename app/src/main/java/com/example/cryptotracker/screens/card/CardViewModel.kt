package com.example.cryptotracker.screens.card

import androidx.lifecycle.ViewModel
import com.example.cryptotracker.domain.usecase.CardUseCase
import com.example.cryptotracker.screens.common.ScreenNavigator

class CardViewModel(
    private val useCase: CardUseCase,
    private val navigator: ScreenNavigator
) : ViewModel() {
    // TODO: Implement the ViewModel
}