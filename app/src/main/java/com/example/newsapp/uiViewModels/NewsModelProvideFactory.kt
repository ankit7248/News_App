package com.example.newsapp.uiViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.NewsRepository


class NewsModelProvideFactory( val newsRepository: NewsRepository) : ViewModelProvider.Factory
{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository)as T
    }
}