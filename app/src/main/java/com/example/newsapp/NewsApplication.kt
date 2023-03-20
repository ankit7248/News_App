package com.example.newsapp

import android.app.Application
import com.example.newsapp.db.ArticleDatabase

class NewsApplication: Application(){

    lateinit var newsRepository: NewsRepository

    override fun onCreate() {
        super.onCreate()
        intialize()

    }

    private fun intialize() {

        val Database =  ArticleDatabase.getDatabase(applicationContext)
        newsRepository = NewsRepository(Database,applicationContext)

    }
}