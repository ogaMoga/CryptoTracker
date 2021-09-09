package com.example.cryptotracker.screens.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.domain.model.CoinListResource
import com.example.cryptotracker.domain.model.Status
import com.example.cryptotracker.domain.usecase.ListUseCase
import com.example.cryptotracker.screens.common.ScreenNavigator
import kotlinx.coroutines.launch

class StartViewModel(
    private val useCase: ListUseCase,
    private val navigator: ScreenNavigator
) : ViewModel() {
    private val _coinListLiveData = MutableLiveData<CoinListResource>()

    val coinListLiveData: LiveData<CoinListResource> = _coinListLiveData

    fun loadData() {
        viewModelScope.launch {
            postLoadingResource()
            val result = useCase.loadCoinList()
            postLiveData(result)
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            postLoadingResource()
            val result = useCase.refreshCoinList()
            postLiveData(result)
        }
    }

    fun setFavourite(name: String, isFavourite: Boolean) {
        viewModelScope.launch {
            useCase.setFavourite(name, isFavourite)
        }
    }

    fun navigateToCard(coinName: String) {
        navigator.toCard(coinName)
    }

    fun navigateToSearch() {
        navigator.toSearch(coinListLiveData.value?.data ?: emptyList())
    }

    private fun postLoadingResource() {
        val resource = CoinListResource(Status.LOADING, null)
        postLiveData(resource)
    }

    private fun postLiveData(coinListResource: CoinListResource) {
        _coinListLiveData.postValue(coinListResource)
    }
}