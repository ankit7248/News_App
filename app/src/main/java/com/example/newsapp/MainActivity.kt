package com.example.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider

import com.example.newsapp.uiViewModels.NewsModelProvideFactory
import com.example.newsapp.uiViewModels.NewsViewModel


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

        val NewsRepository = (application as NewsApplication).newsRepository
        val viewModelProviderFactory = NewsModelProvideFactory(NewsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


    }

}
