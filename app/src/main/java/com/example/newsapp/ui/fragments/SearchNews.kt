package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsapp.NewsActivity
import com.example.newsapp.R
import com.example.newsapp.uiViewModels.NewsViewModel

class SearchNews : Fragment (R.layout.fragment_search){

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}