package com.example.cryptotracker.screens.list

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R
import com.example.cryptotracker.data.Coin

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cv: CardView = itemView.findViewById(R.id.cv)
    private val logo: ImageView = itemView.findViewById(R.id.cv_logo)  // implement later
    private val name: TextView = itemView.findViewById(R.id.coin_title)
    private val desc: TextView = itemView.findViewById(R.id.coin_desc)
    private val star: CheckBox = itemView.findViewById(R.id.cv_fav)
    private val price: TextView = itemView.findViewById(R.id.coin_price)
    private val diff: TextView = itemView.findViewById(R.id.coin_diff)

    public fun bind(coin: Coin) {
        name.text = coin.name
        desc.text = coin.desc
        star.isChecked = coin.isFav
        price.text = coin.price.toString()
        diff.text = coin.priceDiff.toString()
    }
}
