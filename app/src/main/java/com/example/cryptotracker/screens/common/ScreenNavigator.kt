package com.example.cryptotracker.screens.common

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.cryptotracker.App
import com.example.cryptotracker.R

class ScreenNavigator(private val navController: NavController) {
    fun toSearch() {
        navController.navigateSafe(R.id.action_startFragment_to_searchFragment)
    }

    fun fromSearchToCard(itemId: Int) {
        val bundle = bundleOf(App.ITEM_ID_KEY to itemId)
        navController.navigateSafe(R.id.action_searchFragment_to_cardFragment, bundle)
    }

    fun fromStartToCard(itemId: Int) {
        val bundle = bundleOf(App.ITEM_ID_KEY to itemId)
        navController.navigateSafe(R.id.action_startFragment_to_cardFragment, bundle)
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    private fun NavController.navigateSafe(
        @IdRes resId: Int,
        args: Bundle? = null
    ) {
        val action = currentDestination?.getAction(resId) ?: graph.getAction(resId)
        if (action != null && currentDestination?.id != action.destinationId) {
            navigate(resId, args)
        }
    }
}