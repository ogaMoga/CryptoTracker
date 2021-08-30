package com.example.cryptotracker.screens.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.domain.model.Coin
import com.example.cryptotracker.domain.usecase.ListUseCase
import com.example.cryptotracker.screens.common.ScreenNavigator
import com.example.cryptotracker.utils.CoinListResource
import com.example.cryptotracker.utils.Status
import kotlinx.coroutines.launch

class StartViewModel(
    private val useCase: ListUseCase,
    private val navigator: ScreenNavigator
) : ViewModel() {
    private val _coinListLiveData = MutableLiveData<CoinListResource>()

    val coinListLiveData: LiveData<CoinListResource> = _coinListLiveData

    fun loadItem() {
        viewModelScope.launch {
            postLoadingResource()
            val result = useCase.loadItems()
//            val resourceByResult = resourceByResult(result)
            postLiveData(result)
        }
    }

    fun setFavourite(position: Int, isFavourite: Boolean) {
        viewModelScope.launch {
            useCase.setFavourite(position, isFavourite)
        }
    }

    fun navigateToCard(coinName: String) {
        navigator.toCard(coinName)
    }

    private fun postLoadingResource() {
        val resource = CoinListResource(Status.LOADING, null)
        postLiveData(resource)
    }

    private fun postLiveData(coinListResource: CoinListResource) {
        _coinListLiveData.postValue(coinListResource)
    }

//    private fun resourceByResult(result: CoinListResource): CoinListResource =
//        if (result == null) {
//            CoinListResource(Status.ERROR, null)
//        } else {
//            CoinListResource(Status.SUCCESS, result)
//        }


}