package com.example.newsapp.uiViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.NewsRepository
import com.example.newsapp.UTIL.Resource
import com.example.newsapp.models.NewsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel()
{
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()  // Mutable live data alwayes check there is changes in breaking news page or not !
    var breakingNewsPage = 1

    init {
        getBreakingNews("IN")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())  // we post the loading state

        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingResponse(response))
    // When changing the data on the Main thread, use the MutableLiveData class’s setValue method,
    // and when changing the LiveData on the background thread, use the MutableLiveData class’s postValue method

    }


    // we check In response there is error or not
    private fun handleBreakingResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let{
                resultResponse -> return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}