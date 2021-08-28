package com.example.cryptotracker.data.api

import com.example.cryptotracker.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = BuildConfig.BaseUrl

    private var gson = GsonBuilder()
//        .setLenient() if error
        .create()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .addConverterFactory(ScalarsConverterFactory.create()) if error
            .addConverterFactory(GsonConverterFactory.create(/*gson*/))
            .build()
    }
}