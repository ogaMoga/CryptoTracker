package com.example.cryptotracker.screens.start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R

class PagerAdapter(
    private val inflater: LayoutInflater,
    private val stockAdapter: CoinAdapter,
    private val favouriteAdapter: CoinAdapter
) : RecyclerView.Adapter<PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(inflater.inflate(R.layout.fragment__list, parent, false))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        when (position) {
            0 -> holder.bind(stockAdapter)
            1 -> holder.bind(favouriteAdapter)
        }
    }

    override fun getItemCount(): Int = 2
}

class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var recyclerView: RecyclerView = itemView.findViewById(R.id.fragment__list_rv)

    fun bind(coinAdapter: CoinAdapter) {
        recyclerView.swapAdapter(coinAdapter, false)
    }
}