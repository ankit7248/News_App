package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.models.Article

interface ArticleDao {

    //Onconflict determine the if databsase stored then it can replace the database

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(article: Article):Long

    @Query("SELECT * FROM articles")
    fun getALLArticles(): LiveData<List<Article>>

    // Live data observe that changes in the list articles and update to to all thread , fragment , activity

    @Delete
    suspend fun deleteArticle(article: Article)
}