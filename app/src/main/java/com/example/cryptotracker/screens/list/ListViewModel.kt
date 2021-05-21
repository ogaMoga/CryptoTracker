package com.example.cryptotracker.screens.list

import ApiResponse
import androidx.lifecycle.*
import com.example.cryptotracker.api.CoinsApi
import com.example.cryptotracker.data.Coin
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

class ListViewModel : ViewModel() {

    fun onStarClicked(name: String, state: Boolean) {
        _stocksCoins.value?.find { coin -> coin.name == name }?.isFav = state
        _favCoins.value = _stocksCoins.value?.filter { coin -> coin.isFav }?.toMutableList()
    }

    private val _stocksCoins = MutableLiveData<MutableList<Coin>>()
    val stocksCoins: LiveData<MutableList<Coin>>
        get() = if (_stocksCoins.value != null) _stocksCoins else {
            _stocksCoins.value = emptyList<Coin>().toMutableList()
            _stocksCoins
        }

    private val _favCoins = MutableLiveData<MutableList<Coin>>()
    val favCoins: LiveData<MutableList<Coin>>
        get() = if (_favCoins.value != null) _favCoins else {
            _favCoins.value = emptyList<Coin>().toMutableList()
            _favCoins
        }

    private val _apiResponse = MutableLiveData<String>()
    val apiResponse: LiveData<String>
        get() = _apiResponse

    private val stocksCoinsObserver = Observer<MutableList<Coin>> { updateFavList() }

    init {
        updateStocksCoins()

        _stocksCoins.observeForever {
            stocksCoinsObserver
        }

    }

    private fun updateFavList() {
        _favCoins.value = _stocksCoins.value?.filter { coin -> coin.isFav }?.toMutableList()
    }

    private fun updateStocksCoins() {
        val oldFavs = _stocksCoins.value?.map { coin -> coin.name to coin.isFav }?.toMap() ?: emptyMap()
        getCoinsFromApi(oldFavs)

    }

    private fun getCoinsFromApi(oldFavs: Map<String, Boolean>) {
              viewModelScope.launch {
                try {
                    _stocksCoins.value = CoinsApi.retrofitService.getCoins().toCoinsList(oldFavs)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
              }
    }



    override fun onCleared() {
        super.onCleared()
        _stocksCoins.removeObserver(stocksCoinsObserver)
    }
}



private fun ApiResponse.toCoinsList(oldFavs: Map<String, Boolean>): MutableList<Coin> =
     data.map { coin -> Coin(coin.symbol, coin.name,
         BigDecimal(coin.quote.usd.price).setScale(3, RoundingMode.HALF_EVEN).toDouble(),
         BigDecimal(coin.quote.usd.percent_change_24h).setScale(3, RoundingMode.HALF_EVEN).toDouble(),
         oldFavs[coin.symbol]?:false) }.toMutableList()

