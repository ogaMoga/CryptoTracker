package com.example.cryptotracker.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.App
import com.example.cryptotracker.R
import com.example.cryptotracker.appComponent
import com.example.cryptotracker.domain.model.Coin
import com.example.cryptotracker.screens.rv.CoinAdapter
import com.example.cryptotracker.screens.rv.CoinClickListener
import com.example.cryptotracker.screens.rv.StarClickListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import javax.inject.Inject

class SearchFragment : Fragment() {
    private lateinit var etSearch: AppCompatEditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyHolder: View
    private lateinit var cgPopular: ChipGroup
    private lateinit var cgHistory: ChipGroup

    private lateinit var coins: CoinAdapter

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__search, container, false)

        rootView.apply {
            etSearch = findViewById(R.id.fragment__search__et)
            recyclerView = findViewById(R.id.fragment__search__rv)
            emptyHolder = findViewById(R.id.fragment__search__empty_holder)
            cgPopular = findViewById(R.id.fragment__search_empty__popular_cg)
            cgHistory = findViewById(R.id.fragment__search_empty__history_cg)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arguments?.getParcelableArrayList<Coin>(App.COIN_LIST_KEY) ?: emptyList()
        viewModel.putCoinsList(list)

        viewModel.loadData()

        coins = CoinAdapter(layoutInflater, starListener, cardClickListener)

        recyclerView.adapter = coins

        viewModel.coinListLiveData.observe(viewLifecycleOwner, { coinList ->

            if (coinList.isEmpty()) { //  place for not-found screen
                setVisibility(isEmptyHolderVisible = true)
            } else {
                setVisibility(isCoinListVisible = true)
                coins.showCoins(coinList)
            }
        })

        viewModel.historyLiveData.observe(viewLifecycleOwner, {nameList ->
            cgHistory.setChips(nameList)
        })

        viewModel.popularLiveData.observe(viewLifecycleOwner, {nameList ->
            cgPopular.setChips(nameList)
        })

        etSearch.doOnTextChanged { text, _, _, _ ->
            if (text?.isEmpty() == true) {
                setVisibility(isEmptyHolderVisible = true)
            } else {
                viewModel.queryChanged(text.toString())
            }
        }
    }

    private val starListener = object: StarClickListener {
        override fun onStarClicked(position: Int, isChecked: Boolean) {
            viewModel.setFavourite(coins.getCoin(position).name, isChecked)
        }
    }

    private val cardClickListener = object: CoinClickListener {
        override fun onCoinClicked(coinName: String) {
            viewModel.navigateToCard(coinName)
        }
    }

    private val chipClickListener = View.OnClickListener { v -> viewModel.navigateToCard((v as Chip).text.toString()) }

    private fun setVisibility(isEmptyHolderVisible: Boolean = false, isCoinListVisible: Boolean = false) {
        emptyHolder.visibility = if (isEmptyHolderVisible) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isCoinListVisible) View.VISIBLE else View.GONE
    }

    private fun ChipGroup.setChips(nameList: List<String>?) {
        this.removeAllViews()
        if (nameList != null) {
            for (name in nameList) {
                val chip = Chip(context)
                chip.text = name

                val chipDrawable = ChipDrawable.createFromAttributes(
                    context,
                    null,
                    0,
                    R.style.chipStyle
                )
                chip.setChipDrawable(chipDrawable)
                chip.isCheckable = false
                chip.setOnClickListener(chipClickListener)
                this.addView(chip)
            }
        }
    }

    companion object {
        fun newInstance(list: List<Coin>): Fragment{
            val args = Bundle()
            args.putParcelableArrayList(App.COIN_LIST_KEY, ArrayList(list))
            val fragment = SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }
}