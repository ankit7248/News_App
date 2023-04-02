package com.example.newsapp.db

import android.content.Context
import androidx.room.*
import com.example.newsapp.models.Article


@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(CrossCareConverters::class)
abstract class ArticleDatabase : RoomDatabase()

{
    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile // other thread can see what changes in the instance ArticleDatabase

        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()



//        fun getDatabase(context: Context): ArticleDatabase?  // CREATED A SINGLETON OF THAT DATABASE
//        {
//            if (instance == null) {  // if it is null we will set that instance in the syncronize block
//                synchronized(ArticleDatabase::class) {  // In this fun the database don't go any other thread database go in ONE thread at a time
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        ArticleDatabase::class.java, "article.db.db"
//                    ).build()
//                }
//            }
//            return instance
//        }
    }

}






