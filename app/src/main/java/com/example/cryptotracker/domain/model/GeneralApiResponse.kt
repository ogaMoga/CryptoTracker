package com.example.cryptotracker.domain.model

import com.google.gson.annotations.SerializedName

data class GeneralApiResponse (
    @SerializedName("data") val data : List<GeneralApiData>,
    @Transient @SerializedName("status") val status : JsonStatus
)

data class GeneralApiData (
    @SerializedName("name") val name : String,
    @SerializedName("symbol") val symbol : String,
    @SerializedName("quote") val quote : GeneralApiQuote,
    @SerializedName("id") val id : Int,
    @Transient @SerializedName("slug") val slug : String,
    @Transient @SerializedName("num_market_pairs") val num_market_pairs : Int,
    @Transient @SerializedName("date_added") val date_added : String,
    @Transient @SerializedName("tags") val tags : List<String>,
    @Transient @SerializedName("max_supply") val max_supply : Int,
    @Transient @SerializedName("circulating_supply") val circulating_supply : Int,
    @Transient @SerializedName("total_supply") val total_supply : Int,
    @Transient @SerializedName("platform") val platform : String,
    @Transient @SerializedName("cmc_rank") val cmc_rank : Int,
    @Transient @SerializedName("last_updated") val last_updated : String
)

data class GeneralApiQuote (
    @SerializedName("USD") val usd : GeneralApiUsd
)

data class JsonStatus (
    @Transient @SerializedName("timestamp") val timestamp : String,
    @Transient @SerializedName("error_code") val error_code : Int,
    @Transient @SerializedName("error_message") val error_message : String,
    @Transient @SerializedName("elapsed") val elapsed : Int,
    @Transient @SerializedName("credit_count") val credit_count : Int,
    @Transient @SerializedName("notice") val notice : String,
    @Transient @SerializedName("total_count") val total_count : Int
)

data class GeneralApiUsd (
    @SerializedName("price") val price : Double,
    @SerializedName("percent_change_24h") val percent_change_24h : Double,
    @Transient @SerializedName("volume_24h") val volume_24h : Double,
    @Transient @SerializedName("percent_change_1h") val percent_change_1h : Double,
    @Transient @SerializedName("percent_change_7d") val percent_change_7d : Double,
    @Transient @SerializedName("percent_change_30d") val percent_change_30d : Double,
    @Transient @SerializedName("percent_change_60d") val percent_change_60d : Double,
    @Transient @SerializedName("percent_change_90d") val percent_change_90d : Double,
    @Transient @SerializedName("market_cap") val market_cap : Double,
    @Transient @SerializedName("last_updated") val last_updated : String
)