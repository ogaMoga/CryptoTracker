package com.example.cryptotracker.screens.start

import androidx.lifecycle.ViewModel
import com.example.cryptotracker.domain.usecase.ListUseCase
import com.example.cryptotracker.screens.common.ScreenNavigator

class StartViewModel(
    private val useCase: ListUseCase,
    private val navigator: ScreenNavigator
) : ViewModel() {
    // TODO: Implement the ViewModel
}