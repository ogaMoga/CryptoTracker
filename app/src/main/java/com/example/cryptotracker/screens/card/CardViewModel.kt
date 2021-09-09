package com.example.cryptotracker.screens.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.domain.model.CoinCardResource
import com.example.cryptotracker.domain.model.Status
import com.example.cryptotracker.domain.usecase.CardUseCase
import com.example.cryptotracker.screens.common.ScreenNavigator
import kotlinx.coroutines.launch

class CardViewModel(
    private val useCase: CardUseCase,
    private val navigator: ScreenNavigator
) : ViewModel() {
    private val _cardLiveData = MutableLiveData<CoinCardResource>()
    val cardLiveData: LiveData<CoinCardResource> = _cardLiveData

    private var coinName: String = ""

    fun loadData(name: String) {
        viewModelScope.launch {
            coinName = name
            postLoadingResource()
            val result = useCase.loadCoinDetails(name)
            postLiveData(result)
        }
    }

    fun setFavourite(isFavourite: Boolean) {
        viewModelScope.launch {
            useCase.setFavourite(coinName, isFavourite)
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            postLoadingResource()
            val result = useCase.refreshCoinDetails(coinName)
            postLiveData(result)
        }
    }

    fun navigateBack() {
        navigator.navigateUp()
    }

    private fun postLoadingResource() {
        val resource = CoinCardResource(Status.LOADING, null)
        postLiveData(resource)
    }

    private fun postLiveData(coinListResource: CoinCardResource) {
        _cardLiveData.postValue(coinListResource)
    }
}