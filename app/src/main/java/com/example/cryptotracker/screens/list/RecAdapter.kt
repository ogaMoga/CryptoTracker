package com.example.cryptotracker.screens.list

import android.net.sip.SipSession
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R
import com.example.cryptotracker.data.Coin
import java.util.function.Consumer

class RecAdapter(private var values: MutableList<Coin>, private val onStarClickListener: OnStarClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.snippet, parent, false)
        return CoinViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = values[position]
        (holder as CoinViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    fun onStarClicked(position: Int, state: Boolean) {
        onStarClickListener.onStarClicked(values[position].name, state)
    }

    fun setCoins(values: MutableList<Coin>) {
        this.values = values
        notifyDataSetChanged()
    }


    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cv: CardView = itemView.findViewById(R.id.cv)
        private val logo: ImageView = itemView.findViewById(R.id.cv_logo)  // implement later
        private val name: TextView = itemView.findViewById(R.id.coin_title)
        private val desc: TextView = itemView.findViewById(R.id.coin_desc)
        private val star: CheckBox = itemView.findViewById(R.id.cv_fav)
        private val price: TextView = itemView.findViewById(R.id.coin_price)
        private val diff: TextView = itemView.findViewById(R.id.coin_diff)

        fun bind(coin: Coin) {
            name.text = coin.name
            desc.text = coin.desc
            star.isChecked = coin.isFav
            price.text = coin.price.toString()
            diff.text = coin.priceDiff.toString()
        }

        init {
            star.setOnClickListener {
                onStarClicked(adapterPosition, star.isChecked)
            }
        }
    }

}

interface OnStarClickListener {
    fun onStarClicked(name: String, state: Boolean)

}

