package com.example.cryptotracker.screens.card

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.cryptotracker.R
import com.google.android.material.tabs.TabLayout
import com.jjoe64.graphview.GraphView

class CardFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var title: TextView
    private lateinit var star: CheckBox
    private lateinit var graph: GraphView

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.card_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.initView()

        // place for on click listeners

    }

    private fun View.initView() {
        toolbar = findViewById(R.id.card_tb)
        title = findViewById(R.id.card_title)
        star = findViewById(R.id.card_star)
        graph = findViewById(R.id.card_graph)

        navController = findNavController()
    }

}