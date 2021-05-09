package com.example.cryptotracker.screens.search.favourite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptotracker.R

class SearchFavouriteFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFavouriteFragment()
    }

    private lateinit var viewModel: SearchFavouriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_favourite_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchFavouriteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}