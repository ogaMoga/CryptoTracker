package com.example.cryptotracker.screens.list.stocks.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R
import com.example.cryptotracker.data.Coin
import com.example.cryptotracker.screens.list.ListViewModel
import com.example.cryptotracker.screens.list.OnStarClickListener
import com.example.cryptotracker.screens.list.RecAdapter

class FavouriteFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    private lateinit var rv: RecyclerView

    private lateinit var adapter: RecAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        view.initView()
        viewModel.favCoins.observe(viewLifecycleOwner, { adapter.setCoins(it) })

    }

    private fun View.initView() {
        rv = findViewById(R.id.favourite_rv)
        adapter = RecAdapter(viewModel.favCoins.value!!, object : OnStarClickListener {
            override fun onStarClicked(name: String, state: Boolean) {
                starClicked(name, state)
            }
        })
        rv.adapter = adapter
    }

    private fun starClicked(name: String, state: Boolean) {
        viewModel.onStarClicked(name, state)
    }

}


