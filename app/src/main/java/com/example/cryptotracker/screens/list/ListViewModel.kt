package com.example.cryptotracker.screens.list

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptotracker.data.Coin

class ListViewModel : ViewModel() {

    fun onStarClicked(name: String, state: Boolean) {
        _stocksCoins.value?.forEach { coin -> if (coin.name == name) coin.isFav = state }
        _favCoins.value = _stocksCoins.value?.filter { coin -> coin.isFav }?.toMutableList()
    }

    private val _stocksCoins = MutableLiveData<MutableList<Coin>>()
    val stocksCoins : LiveData<MutableList<Coin>>
        get() = _stocksCoins

    private val _favCoins = MutableLiveData<MutableList<Coin>>()
    val favCoins : LiveData<MutableList<Coin>>
        get() = _favCoins

    init {
        _stocksCoins.value = mutableListOf<Coin>(Coin("BTC", "Bitcoin", 60000.toDouble(), 0.toDouble(), true),
                Coin("ETH", "Ethereum", 3000.toDouble(), 0.toDouble(), true),
                Coin("XRP", "XRP", 1.46.toDouble(), 0.toDouble(), true),
                Coin("BNB", "Binance Coin", 1476.toDouble(), 0.toDouble(), false),
                Coin("ADA", "Cardano", 1.74.toDouble(), 0.toDouble(), true),
                Coin("DOGE", "Dogecoin", 60000.toDouble(), 0.toDouble(), false))
        _favCoins.value = _stocksCoins.value!!.filter { coin -> coin.isFav }.toMutableList()
    }

}