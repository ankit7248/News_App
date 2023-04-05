package com.example.newsapp.api

import com.example.newsapp.models.NewsResponse
import com.example.newsapp.UTIL.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    // only important headlines request call

@GET("v2/top-headlines")  // we want to get data from api in breaking news
suspend fun getBreakingNews(
    @Query("country")
    countryCode: String ="IN",
    @Query("page")
    pageNumber: Int = 1,
    @Query("apikey")
    apiKey: String = API_KEY
):Response<NewsResponse>

//search any news

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        countryCode: String ="IN",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ):Response<NewsResponse>
}