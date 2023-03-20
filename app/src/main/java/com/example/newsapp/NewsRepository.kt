package com.example.newsapp

import android.content.Context
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase

class NewsRepository(
    private val articleDatabase: ArticleDatabase?,
    private val applicationContext: Context
) {
    suspend fun getBreakingNews(countryCode: String,pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}