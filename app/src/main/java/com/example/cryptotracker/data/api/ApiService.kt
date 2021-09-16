package com.example.cryptotracker.data.api

import com.example.cryptotracker.BuildConfig
import com.example.cryptotracker.domain.model.GeneralApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

private const val API_KEY = BuildConfig.ApiKey
private const val TOP_200 = "v1/cryptocurrency/listings/latest?limit=200&convert=USD"

interface ApiService {
    @Headers(
        "Accept: application/json",
        "X-CMC_PRO_API_KEY: $API_KEY"
    )
    @GET(TOP_200)
    fun getCoinsList(): Call<GeneralApiResponse>
}