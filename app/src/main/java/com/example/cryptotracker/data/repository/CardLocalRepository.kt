package com.example.cryptotracker.data.repository

import com.example.cryptotracker.data.database.dao.CardDao

class CardLocalRepository(
    private val dao: CardDao
) {
    suspend fun getGraphData(name: String): String { // change to graphData
        return ""
    }

    suspend fun addGraphData(data: String) {

    }
}
