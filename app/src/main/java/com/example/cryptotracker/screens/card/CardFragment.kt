package com.example.cryptotracker.screens.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import com.example.cryptotracker.App
import com.example.cryptotracker.R
import com.github.mikephil.charting.charts.LineChart
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardFragment : Fragment() {
    private lateinit var btnBack: AppCompatImageButton
    private lateinit var cbStar: AppCompatCheckBox
    private lateinit var graph: LineChart

    private val viewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__card, container, false)
        rootView.apply {
            btnBack = findViewById(R.id.fragment__card__back)
            cbStar = findViewById(R.id.fragment__card__star)
            graph = findViewById(R.id.fragment__card__graph)
        }
        return rootView
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
