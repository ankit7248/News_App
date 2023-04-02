package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.models.Article

@Dao
interface ArticleDao {

    //Onconflict determine that if database stored then it can replace the database

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(article: Article):Long

    @Query("SELECT * FROM articles")
    fun getALLArticles(): LiveData<List<Article>>

    // Live data observe that changes in the list articles and update to to all thread , fragment , activity

    @Delete
    suspend fun deleteArticle(article: Article)
}