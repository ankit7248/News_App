package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.models.Article


@Database(
    entities = [Article::class],
    version = 1
)


abstract class ArticleDatabase : RoomDatabase()

{
    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile // other thread can see what changes in the instance ArticleDatabase

        private var instance: ArticleDatabase? = null

        fun getDatabase(context: Context): ArticleDatabase?  // CREATED A SINGLETON OF THAT DATABASE
        {
            if (instance == null) {  // if it is null we will set that instance in the syncronize block
                synchronized(ArticleDatabase::class) {  // In this fun the database don't go any other thread database go in ONE thread at a time
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java, "article.db.db"
                    ).build()
                }
            }
            return instance
        }
    }

}






