package com.example.cryptotracker.data.repository

import com.example.cryptotracker.data.api.ApiService
import com.example.cryptotracker.domain.model.GeneralApiResponse
import com.example.cryptotracker.exception.LostConnectionException
import com.example.cryptotracker.exception.WrongResponseException
import javax.inject.Inject

class ListRemoteRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getGeneralInfo(): GeneralApiResponse {
        val response = generalResponse()

        if (response.isSuccessful) {
            return response.body()!!
        } else throw WrongResponseException(response.message())
    }

    private fun generalResponse() = try {
        apiService.getCoinsList().execute()
    } catch (e: Exception) {
        throw LostConnectionException()
    }
}
