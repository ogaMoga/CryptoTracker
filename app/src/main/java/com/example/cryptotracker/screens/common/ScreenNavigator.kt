package com.example.cryptotracker.screens.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.cryptotracker.R
import com.example.cryptotracker.domain.model.Coin
import com.example.cryptotracker.screens.card.CardFragment
import com.example.cryptotracker.screens.search.SearchFragment
import com.example.cryptotracker.screens.start.StartFragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions

class ScreenNavigator() : FragNavController.RootFragmentListener {
    private lateinit var fragNavController: FragNavController

    fun init(fragmentManager: FragmentManager, savedInstanceState: Bundle?) {
        fragNavController = FragNavController(fragmentManager, R.id.activity_container)
        fragNavController.apply {
            rootFragmentListener = this@ScreenNavigator
            defaultTransactionOptions = FragNavTransactionOptions
                .newBuilder()
                .customAnimations(
                    R.anim.fade_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.fade_out,
                )
                .build()
            initialize(FragNavController.TAB1, savedInstanceState)
        }
    }

    override val numberOfRootFragments: Int
        get() = 1

    override fun getRootFragment(index: Int): Fragment =
        StartFragment.newInstance()

    fun onSaveInstanceState(outState: Bundle?) {
        fragNavController.onSaveInstanceState(outState)
    }

    fun toSearch(data: List<Coin>) {
        fragNavController.pushFragment(SearchFragment.newInstance(data))
    }

    fun toCard(coinName: String) {
        fragNavController.pushFragment(CardFragment.newInstance(coinName))
    }

    fun navigateUp(): Boolean =
    if (fragNavController.isRootFragment) {
        false
    } else {
        fragNavController.popFragment()
        true
    }
}