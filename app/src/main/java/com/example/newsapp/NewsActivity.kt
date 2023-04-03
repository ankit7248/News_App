package com.example.newsapp
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.uiViewModels.NewsModelProvideFactory
import com.example.newsapp.uiViewModels.NewsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewsActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    lateinit var viewModel: NewsViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        binding?.bottomNavigationView?.setupWithNavController(navController)

        val Repository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsModelProvideFactory(Repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


    }
//    override fun onDestroy() {
//        super.onDestroy()
//        binding = null
//    }

}
