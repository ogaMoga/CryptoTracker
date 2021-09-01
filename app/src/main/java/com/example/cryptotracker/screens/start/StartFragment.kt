package com.example.cryptotracker.screens.start

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptotracker.R
import com.example.cryptotracker.domain.model.Status
import com.example.cryptotracker.screens.rv.CoinAdapter
import com.example.cryptotracker.screens.rv.CoinClickListener
import com.example.cryptotracker.screens.rv.PagerAdapter
import com.example.cryptotracker.screens.rv.StarClickListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {
    private lateinit var etSearch: AppCompatEditText
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var stockCoins: CoinAdapter
    private lateinit var favouriteCoins: CoinAdapter

    private val viewModel: StartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__start, container, false)
        rootView.apply {
            etSearch = findViewById(R.id.fragment__start__search_et)
            tabLayout = findViewById(R.id.fragment__start__tablayout)
            viewPager = findViewById(R.id.fragment__start__viewpager)
            swipeRefreshLayout = findViewById(R.id.fragment__start__swipe_container)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stockCoins = CoinAdapter(layoutInflater, stockStarListener, cardClickListener)

        favouriteCoins = CoinAdapter(layoutInflater, favouriteStarListener, cardClickListener)

        viewPager.adapter = PagerAdapter(layoutInflater, stockCoins, favouriteCoins)

        viewModel.coinListLiveData.observe(viewLifecycleOwner, { coinListResource ->
            when(coinListResource.status) {
                Status.LOADING -> {
                    swipeRefreshLayout.isRefreshing = true
                }
                Status.SUCCESS -> {
                    swipeRefreshLayout.isRefreshing = false
                    stockCoins.showCoins(coinListResource.data!!)
                    favouriteCoins.showCoins(coinListResource.data.filter { coin -> coin.isFavourite })
                }
                Status.ERROR -> {
                    swipeRefreshLayout.isRefreshing = false
                }
            }

        })

        etSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
               viewModel.navigateToSearch()
            }
        }

        swipeRefreshLayout.setOnRefreshListener { viewModel.refreshData() }

        tabLayoutConfiguration()

        viewModel.loadItem()
    }

    private val stockStarListener = object : StarClickListener {
        override fun onStarClicked(position: Int, isChecked: Boolean) {
            val coin = stockCoins.getCoin(position)
            viewModel.setFavourite(coin.name, isChecked)
            stockCoins.setFavourite(position, isChecked)
            if (isChecked) {
                favouriteCoins.addCoin(coin)
            } else {
                favouriteCoins.removeCoin(coin)
            }
        }
    }

    private val favouriteStarListener = object : StarClickListener {
        override fun onStarClicked(position: Int, isChecked: Boolean) {
            val coin = favouriteCoins.getCoin(position)
            viewModel.setFavourite(coin.name, false)
            stockCoins.setNotFavourite(coin)
            favouriteCoins.removeCoin(position)
        }
    }

    private val cardClickListener = object: CoinClickListener {
        override fun onCoinClicked(coinName: String) {
            viewModel.navigateToCard(coinName)
        }
    }

    private fun tabLayoutConfiguration() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val tabContainer = LayoutInflater.from(context)
                .inflate(R.layout.custom_tab_item, tabLayout, false) as ViewGroup?
            if (tabContainer != null) {
                val textView =
                    tabContainer.findViewById<AppCompatTextView>(R.id.custom_tab_item__tv)
                when (position) {
                    0 -> {
                        textView.text = getString(R.string.stocks_tab_title)
                        textView.makeSelectedStyle()
                    }
                    1 -> {
                        textView.text = getString(R.string.favourite_tab_title)
                        textView.makeUnselectedStyle()
                    }
                    else -> {
                    }
                }
                tab.customView = tabContainer
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            private fun TabLayout.Tab.getTextView(): AppCompatTextView? {
                val customView: View = customView ?: return null
                return customView.findViewById(R.id.custom_tab_item__tv)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val textView = tab?.getTextView() ?: return
                textView.makeSelectedStyle()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val textView = tab?.getTextView() ?: return
                textView.makeUnselectedStyle()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun AppCompatTextView.makeSelectedStyle() {
        textSize = 20f
        setTextColor(Color.BLACK)
    }

    private fun AppCompatTextView.makeUnselectedStyle() {
        textSize = 18f
        setTextColor(Color.GRAY)
    }

    companion object {
        fun newInstance(): Fragment = StartFragment()
    }
}