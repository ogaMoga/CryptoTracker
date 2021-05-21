package com.example.cryptotracker.api

import ApiResponse
import com.example.cryptotracker.BuildConfig
import com.example.cryptotracker.data.Coin
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

private const val BASE_URL = "https://pro-api.coinmarketcap.com/"
private const val API_KEY = BuildConfig.ApiKey

private const val TOP_20 = "v1/cryptocurrency/listings/latest?limit=20&convert=USD"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CoinApiService {
    @Headers(
        "Accept: application/json",
        "X-CMC_PRO_API_KEY: $API_KEY"
    )
    @GET(TOP_20)
    suspend fun getCoins(): ApiResponse

}

object CoinsApi {
    val retrofitService: CoinApiService by lazy { retrofit.create(CoinApiService::class.java) }
}