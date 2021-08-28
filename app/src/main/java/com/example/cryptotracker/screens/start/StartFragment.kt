package com.example.cryptotracker.screens.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.cryptotracker.R
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {
    private lateinit var etSearch: AppCompatEditText
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    private val viewModel: StartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__start, container, false)
        rootView.apply {
            etSearch = findViewById(R.id.fragment__start__search_et)
            tabLayout = findViewById(R.id.fragment__start__tablayout)
            viewPager = findViewById(R.id.fragment__start__viewpager)
        }
        return rootView
    }
}