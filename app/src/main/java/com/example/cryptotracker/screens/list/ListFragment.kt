package com.example.cryptotracker.screens.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.cryptotracker.R
import com.google.android.material.tabs.TabLayout


class ListFragment : Fragment() {

    private lateinit var searchView : SearchView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.initViews()

        // place for click listeners

    }

    private fun View.initViews() {
        searchView = findViewById(R.id.searchview)
        tabLayout = findViewById(R.id.sliding_tabs)
        viewPager = findViewById(R.id.viewpager)

        navController = findNavController()
    }

}
