package com.example.cryptotracker.screens.list.stocks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R
import com.example.cryptotracker.data.Coin
import com.example.cryptotracker.screens.list.ListViewModel
import com.example.cryptotracker.screens.list.OnStarClickListener
import com.example.cryptotracker.screens.list.RecAdapter

class StocksFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    private lateinit var rv: RecyclerView

    private lateinit var adapter: RecAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stocks_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        view.initView()
        viewModel.stocksCoins.observe(viewLifecycleOwner, { adapter.setCoins(it) })

    }

    private fun View.initView() {
        rv = findViewById(R.id.stocks_rv)
        adapter = RecAdapter(viewModel.stocksCoins.value!!, object : OnStarClickListener {
            override fun onStarClicked(name: String, state: Boolean) {
                starClicked(name, state)
            }
        })
        rv.adapter = adapter
    }

    private fun starClicked(name: String, state: Boolean) {
        viewModel.onStarClicked(name, state)
    }

    override fun onResume() {
        super.onResume()
        adapter.setCoins(viewModel.stocksCoins.value!!)
    }
}
