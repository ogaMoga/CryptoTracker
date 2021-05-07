package com.example.cryptotracker.screens.search.empty

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptotracker.R

class SearchEmptyFragment : Fragment() {

    companion object {
        fun newInstance() = SearchEmptyFragment()
    }

    private lateinit var viewModel: SearchEmptyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_empty_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchEmptyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}