package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// Room is only work in primitive datatype

@Entity(
    tableName = "articles"
)

data class Article(
    // When we use localDatabse  so, we set the primary key to give the unique id in articles
    @PrimaryKey(autoGenerate = true)  // autoGenerate -> increment of idE
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)