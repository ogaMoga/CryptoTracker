package com.example.cryptotracker.data.repository

import com.example.cryptotracker.data.database.dao.SearchDao
import com.example.cryptotracker.domain.model.Coin

class SearchRepository(
    private val dao: SearchDao
) {
    suspend fun getHistory() : List<String> {
        return emptyList()
    }

    suspend fun getPopular() : List<String> {
        return emptyList()
    }

    suspend fun addPopularList(list: List<String>) {

    }

    suspend fun addRequest(name: List<Coin>) {

    }
}
