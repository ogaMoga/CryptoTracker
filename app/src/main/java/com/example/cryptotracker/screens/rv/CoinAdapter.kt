package com.example.cryptotracker.screens.rv

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptotracker.BuildConfig
import com.example.cryptotracker.R
import com.example.cryptotracker.domain.model.Coin


class CoinAdapter(
    private val inflater: LayoutInflater,
    private val starListener: StarClickListener,
    private val coinListener: CoinClickListener
): RecyclerView.Adapter<CoinViewHolder>()
{
    private val coins: MutableList<Coin> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder =
        CoinViewHolder(inflater.inflate(R.layout.fragment__snippet, parent, false),
            starListener,
            coinListener
        )

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position])
    }

    override fun getItemCount() = coins.size

    fun showCoins(coins: List<Coin>) {
        this.coins.clear()
        this.coins.addAll(coins)
        notifyDataSetChanged()
    }

    fun setFavourite(position: Int, isFavourite: Boolean) {
        coins[position].isFavourite = isFavourite
        notifyItemChanged(position)
    }

    fun setNotFavourite(coin: Coin) {
        setFavourite(coins.indexOf(coin), false)
    }

    fun addCoin(coin: Coin) {
        coins.add(coin)
        notifyItemInserted(itemCount)
    }

    fun removeCoin(coin: Coin) {
        removeCoin(coins.indexOf(coin))
    }

    fun removeCoin(position: Int) {
        coins.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getCoin(position: Int): Coin = coins[position]
}

class CoinViewHolder(
    itemView: View,
    private val starListener: StarClickListener,
    private val coinListener: CoinClickListener
): RecyclerView.ViewHolder(itemView) {
    private var card: CardView = itemView.findViewById(R.id.fragment__snippet__cv)
    private var logo: AppCompatImageView = itemView.findViewById(R.id.fragment__snippet__logo)
    private var title: AppCompatTextView = itemView.findViewById(R.id.fragment__snippet__title)
    private var description: AppCompatTextView = itemView.findViewById(R.id.fragment__snippet__desc)
    private var star: AppCompatCheckBox = itemView.findViewById(R.id.fragment__snippet__star)
    private var price: AppCompatTextView = itemView.findViewById(R.id.fragment__snippet__price)
    private var diff: AppCompatTextView = itemView.findViewById(R.id.fragment__snippet__diff)

    init {
        setStarListener()
        setCoinListener()
    }

    @SuppressLint("SetTextI18n")
    fun bind(coin: Coin) {
        title.text = coin.name
        description.text = coin.description
        Glide.with(logo.context)
            .load(BuildConfig.BaseLogoUrl + coin.logo + ".png")
            .into(logo)

        price.text = "$" + coin.price
        diff.text = coin.diff.toString().substring(0, 4)
        if (coin.diff >= 0) {
            diff.text = "+" + diff.text + "%"
            diff.setTextColor(Color.rgb(36, 178, 93))
        } else {
            diff.text = diff.text.toString() + "%"
            diff.setTextColor(Color.RED)
        }

        star.setOnCheckedChangeListener(null)
        star.isChecked = coin.isFavourite
        setStarListener()
    }

    private fun setStarListener() {
        star.setOnCheckedChangeListener { _, isChecked -> starListener.onStarClicked(adapterPosition, isChecked) }
    }

    private fun setCoinListener() {
        card.setOnClickListener { _ -> coinListener.onCoinClicked(title.text.toString()) }
    }
}

interface StarClickListener {
    fun onStarClicked(position: Int, isChecked: Boolean)
}

interface CoinClickListener {
    fun onCoinClicked(coinName: String)
}


