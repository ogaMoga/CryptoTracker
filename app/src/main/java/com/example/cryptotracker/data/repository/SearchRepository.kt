package com.example.cryptotracker.data.repository

import com.example.cryptotracker.data.database.dao.SearchDao
import com.example.cryptotracker.data.database.model.SearchEntity
import com.example.cryptotracker.exception.DatabaseException
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val dao: SearchDao
) {
    suspend fun getHistory() : List<String> {
        return dao.getHistory()
    }

    suspend fun getPopular() : List<String> {
        val result = dao.getPopular()
        if (result.isNotEmpty()) {
            return result
        } else {
            throw DatabaseException()
        }
    }

    suspend fun addPopularList(list: List<String>) {
        val oldEntities = dao.getEntitiesByNames(list)
        val newEntities = mutableListOf<SearchEntity>()
        for (coin in oldEntities) {
            newEntities.add(SearchEntity(
                name = coin.name,
                isPopular = true,
                time = coin.time
            )
            )
        }
        dao.addPopularList(newEntities)
    }

    suspend fun addRequest(name: String) {
        val oldEntity = dao.getEntityByName(name)
        val newEntity = SearchEntity(
            name = oldEntity?.name ?: name,
            isPopular = oldEntity?.isPopular ?: false,
            time = System.currentTimeMillis()
        )
        dao.addRequest(newEntity)
    }

    suspend fun firstLoad(names: List<String>) {
        val entities = mutableListOf<SearchEntity>()
        for (entity in names) {
            entities.add(
                SearchEntity(
                name = entity,
                isPopular = false,
                time = null
            ))
        }
        dao.addPopularList(entities)
    }
}
