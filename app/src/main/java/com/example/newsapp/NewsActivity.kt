package com.example.newsapp
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity


import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.db.ArticleDatabase

import com.example.newsapp.uiViewModels.NewsModelProvideFactory
import com.example.newsapp.uiViewModels.NewsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class NewsActivity : AppCompatActivity() {

//    private var binding: ActivityMainBinding? = null
    lateinit var bottomNavigationView: BottomNavigationView

    lateinit var viewModel: NewsViewModel
    lateinit var newsNavHostFragment : View
//    lateinit var findNavController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        newsNavHostFragment = findViewById(R.id.newsNavHostFragment)
//        findNavController = findViewById(R.id.)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)
        // when we use fragment container view then we have to use supportFragmentManager
        bottomNavigationView.setupWithNavController(navHostFragment!!.findNavController())


        val Repository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsModelProvideFactory(Repository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


    }
//    override fun onDestroy() {
//        super.onDestroy()
//        binding = null
//    }

}
