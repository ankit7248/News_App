package com.example.newsapp.api

import com.example.newsapp.UTIL.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{

        private val retrofit by lazy{  // lazy means we only intilaize in this curly brackets

            val logging  = HttpLoggingInterceptor()

            // WE see the actual response and request making

            logging.setLevel((HttpLoggingInterceptor.Level.BODY)) // Logging use for debugging
            // WE see the actual response

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                    //determine json response converted to kotlin object
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }

        val api by lazy{
            retrofit.create(NewsApi::class.java)
        }
    }
}