package com.example.cryptotracker.screens.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R
import com.google.android.material.chip.ChipGroup
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private lateinit var etSearch: AppCompatEditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyHolder: View
    private lateinit var cgPopular: ChipGroup
    private lateinit var cgHistory: ChipGroup

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__search, container, false)
        rootView.apply {
            etSearch = findViewById(R.id.fragment__search__et)
            recyclerView = findViewById(R.id.fragment__search__rv)
            emptyHolder = findViewById(R.id.fragment__search__empty_holder)
            cgPopular = findViewById(R.id.fragment__search_empty__popular_cg)
            cgHistory = findViewById(R.id.fragment__search_empty__history_cg)
        }
        return rootView
    }

    companion object {
        fun newInstance(): Fragment = SearchFragment()
    }
}
