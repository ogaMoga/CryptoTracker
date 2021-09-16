package com.example.cryptotracker.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.domain.model.Coin
import com.example.cryptotracker.domain.usecase.SearchUseCase
import com.example.cryptotracker.screens.common.ScreenNavigator
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase,
    private val navigator: ScreenNavigator
) : ViewModel() {
    private val _coinListLiveData = MutableLiveData<List<Coin>>()
    private val _historyLiveData = MutableLiveData<List<String>>()
    private val _popularLiveData = MutableLiveData<List<String>>()

    val coinListLiveData: LiveData<List<Coin>> = _coinListLiveData
    val historyLiveData: LiveData<List<String>> = _historyLiveData
    val popularLiveData: LiveData<List<String>> = _popularLiveData

    private lateinit var coinList: List<Coin>

    fun loadData() {
        viewModelScope.launch {
            val popular = useCase.getPopular()
            val history = useCase.getHistory()
            postHistory(history)
            postPopular(popular)
        }
    }

    fun putCoinsList(list: List<Coin>) {
        coinList = list.sortedByDescending { coin -> coin.isFavourite }
    }

    fun queryChanged(text: String) {
        val query = text.uppercase()
        val matchCoins = coinList.filter { coin ->
            coin.name.contains(query) || coin.description.uppercase().contains(query)
        }
        postSearchList(matchCoins)
    }

    fun setFavourite(name: String, isFavourite: Boolean) {
        viewModelScope.launch {
            useCase.setFavourite(name, isFavourite)
        }
    }

    private fun addToHistory(coinName: String) {
        viewModelScope.launch {
            useCase.addToHistory(coinName)
        }
    }

    fun navigateToCard(coinName: String) {
        addToHistory(coinName)
        navigator.toCard(coinName)
    }

    private fun postSearchList(coinList: List<Coin>) {
        _coinListLiveData.postValue(coinList)
    }

    private fun postHistory(coinList: List<String>) {
        _historyLiveData.postValue(coinList)
    }

    private fun postPopular(coinList: List<String>) {
        _popularLiveData.postValue(coinList)
    }
}