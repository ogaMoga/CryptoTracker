package com.example.cryptotracker.screens.list.stocks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R
import com.example.cryptotracker.screens.list.ListViewModel
import com.example.cryptotracker.screens.list.RecAdapter

class StocksFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    private lateinit var rv: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stocks_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        view.initView()

        // place for click listeners

    }

    private fun View.initView() {
        rv = findViewById(R.id.stocks_rv)
        rv.adapter = RecAdapter(viewModel.stocksCoins.value)
    }

}
