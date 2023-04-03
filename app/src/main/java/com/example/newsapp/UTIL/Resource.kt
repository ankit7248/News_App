package com.example.newsapp.UTIL

sealed class Resource<T> ( //sealed class is just like abstract class
    val data: T? =null, // body of response means actual response
    val message : String? =null  // error message in string which is currently nullable string
        )
{
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?,data: T? = null) : Resource<T>(data,message)
    class Loading<T> : Resource<T>()
}

