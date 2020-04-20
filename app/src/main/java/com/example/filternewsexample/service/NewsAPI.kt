package com.example.filternewsexample.service

import com.example.filternewsexample.model.ArticlesResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/v2/top-headlines?sources=google-news")
    fun getNewsListTop(@Query("apiKey") apiKey: String): Observable<ArticlesResponse>

    @GET("/v2/everything")
    fun getNewsListSearch(@Query("q") keySearch : String, @Query("apiKey") apiKey : String)
            : Observable<ArticlesResponse>
}