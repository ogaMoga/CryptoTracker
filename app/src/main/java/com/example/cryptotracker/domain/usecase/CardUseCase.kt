package com.example.cryptotracker.domain.usecase

import com.example.cryptotracker.data.repository.CardLocalRepository
import com.example.cryptotracker.data.repository.CardRemoteRepository

class CardUseCase(
    private val localRepository: CardLocalRepository,
    private val remoteRepository: CardRemoteRepository
) {

}
