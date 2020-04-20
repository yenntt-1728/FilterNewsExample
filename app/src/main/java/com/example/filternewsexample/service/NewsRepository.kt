package com.example.filternewsexample.service

import android.content.Context
import com.example.filternewsexample.model.ArticlesResponse
import io.reactivex.Observable
import retrofit2.Call

class NewsRepository(private val context: Context) : NewsAPIImpl {
    private val apiNews : NewsAPI = NewsApiInstance.getInstance().newInstance(context)
    override fun getNewsListTop(apiKey: String): Observable<ArticlesResponse> {
        return apiNews.getNewsListTop(apiKey)
    }

    override fun getNewsListSearch(
        keySearch: String,
        apiKey: String
    ): Observable<ArticlesResponse> {
        return apiNews.getNewsListSearch(keySearch, apiKey)
    }
}