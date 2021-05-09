package com.example.cryptotracker.screens.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cryptotracker.screens.list.stocks.StocksFragment
import com.example.cryptotracker.screens.list.stocks.favourite.FavouriteFragment
import com.example.cryptotracker.screens.search.favourite.SearchFavouriteFragment
import com.example.cryptotracker.screens.search.stocks.SearchStocksFragment

class MyPagerAdapter(
    fragmentManager: FragmentManager,
    val isSearch: Boolean,
    int: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) : FragmentPagerAdapter(fragmentManager, int) {

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence = if (position == 0) "Stocks" else "Favourite"

    override fun getItem(position: Int) = if (isSearch) getSearchItems(position) else getAllItems(position)

    private fun getAllItems(position: Int) = if (position == 0) StocksFragment() else FavouriteFragment()
    private fun getSearchItems(position: Int) = if (position == 0) SearchStocksFragment() else SearchFavouriteFragment()
}
