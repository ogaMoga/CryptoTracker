package com.example.cryptotracker.domain.usecase

import com.example.cryptotracker.data.repository.ListLocalRepository
import com.example.cryptotracker.data.repository.ListRemoteRepository

class ListUseCase(
    private val localRepository: ListLocalRepository,
    private val remoteRepository: ListRemoteRepository,
) {

}
