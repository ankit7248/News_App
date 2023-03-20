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
 val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())

        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingResponse(response))
    // When changing the data on the Main thread, use the MutableLiveData class’s setValue method,
    // and when changing the LiveData on the background thread, use the MutableLiveData class’s postValue method

    }

    private fun handleBreakingResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let{
                resultResponse -> return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}