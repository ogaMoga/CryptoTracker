package com.example.cryptotracker.domain.usecase

import com.example.cryptotracker.data.repository.ListLocalRepository
import com.example.cryptotracker.data.repository.SearchRepository
import com.example.cryptotracker.exception.DatabaseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val listRepository: ListLocalRepository
) {
    suspend fun getHistory(): List<String> = withContext(Dispatchers.IO) {
        return@withContext searchRepository.getHistory()
    }

    suspend fun getPopular(): List<String> = withContext(Dispatchers.IO) {
        try {
            return@withContext searchRepository.getPopular()
        } catch (e: DatabaseException) {
            val names = listRepository.getNames()
            searchRepository.firstLoad(names)
            val result = names.shuffled().take(5)
            searchRepository.addPopularList(result)
            return@withContext searchRepository.getPopular()
        }
    }

    suspend fun setFavourite(name: String, isFavourite: Boolean) = withContext(Dispatchers.IO) {
        listRepository.updateFavourite(name, isFavourite)
    }

    suspend fun addToHistory(name: String) = withContext(Dispatchers.IO) {
        searchRepository.addRequest(name)
    }
}
