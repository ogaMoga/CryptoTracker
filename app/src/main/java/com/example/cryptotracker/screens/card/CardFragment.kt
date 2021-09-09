package com.example.cryptotracker.screens.card

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.style.TtsSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cryptotracker.App
import com.example.cryptotracker.BuildConfig
import com.example.cryptotracker.R
import com.example.cryptotracker.domain.model.CardData
import com.example.cryptotracker.domain.model.Status
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat

class CardFragment : Fragment() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var btnBack: AppCompatImageButton
    private lateinit var cbStar: AppCompatCheckBox
    private lateinit var title: AppCompatTextView
    private lateinit var description: AppCompatTextView
    private lateinit var rank: AppCompatTextView
    private lateinit var price: AppCompatTextView
    private lateinit var marketCap: AppCompatTextView
    private lateinit var volume24h: AppCompatTextView
    private lateinit var change1h: AppCompatTextView
    private lateinit var change24h: AppCompatTextView
    private lateinit var change7d: AppCompatTextView
    private lateinit var change30d: AppCompatTextView
    private lateinit var change60d: AppCompatTextView
    private lateinit var change90d: AppCompatTextView

    private val viewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__card, container, false)
        rootView.apply {
            swipeRefreshLayout = findViewById(R.id.fragment__card_swipe)
            btnBack = findViewById(R.id.fragment__card__back)
            cbStar = findViewById(R.id.fragment__card__star)
            title = findViewById(R.id.fragment__card__title)
            description = findViewById(R.id.fragment__card__description)
            rank = findViewById(R.id.fragment__card__rank_value)
            price = findViewById(R.id.fragment__card__price_value)
            marketCap = findViewById(R.id.fragment__card__marketcap_value)
            volume24h = findViewById(R.id.fragment__card__volume24h_value)
            change1h = findViewById(R.id.fragment__card__price1h_value)
            change24h = findViewById(R.id.fragment__card__price24h_value)
            change7d = findViewById(R.id.fragment__card__price7d_value)
            change30d = findViewById(R.id.fragment__card__price30d_value)
            change60d = findViewById(R.id.fragment__card__price60d_value)
            change90d = findViewById(R.id.fragment__card__price90d_value)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameFromArguments = arguments?.getString(App.ITEM_ID_KEY)
        title.text = nameFromArguments

        viewModel.cardLiveData.observe(viewLifecycleOwner, { coinCardResource ->
            when(coinCardResource.status) {
                Status.LOADING -> {
                    swipeRefreshLayout.isRefreshing = true
                }
                Status.SUCCESS -> {
                    swipeRefreshLayout.isRefreshing = false
                    updateData(coinCardResource.data!!)
                }
                Status.ERROR -> {
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        })

        viewModel.loadData(nameFromArguments!!)

        swipeRefreshLayout.setOnRefreshListener { viewModel.refreshData() }

        cbStar.setOnCheckedChangeListener { _, isChecked ->  viewModel.setFavourite(isChecked) }

        btnBack.setOnClickListener { viewModel.navigateBack() }
    }

    @SuppressLint("SetTextI18n")
    private fun updateData(data: CardData) {
        val formatter = NumberFormat.getCurrencyInstance()
        formatter.maximumIntegerDigits = 10
        description.text = data.description
        cbStar.isChecked = data.isFavourite
        rank.text =  "#" + data.rank.toString()
        price.text = formatter.format(data.price)
        marketCap.text = formatter.format(data.marketCap)
        volume24h.text = formatter.format(data.volume24h)
        setDiff(data.percentChange1h, change1h)
        setDiff(data.percentChange24h, change24h)
        setDiff(data.percentChange7d, change7d)
        setDiff(data.percentChange30d, change30d)
        setDiff(data.percentChange60d, change60d)
        setDiff(data.percentChange90d, change90d)
    }

    private fun setDiff(value: Double, textView: AppCompatTextView) {
        textView.text = if (value > 0) { "+$value%" } else { "$value%" }
        textView.setTextColor(if (value > 0) { Color.rgb(36, 178, 93) } else {Color.RED})
    }


    companion object {
        fun newInstance(coinName: String): Fragment {
            val args = Bundle()
            args.putString(App.ITEM_ID_KEY, coinName)
            val fragment = CardFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
