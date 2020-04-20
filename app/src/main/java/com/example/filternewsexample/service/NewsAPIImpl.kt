package com.example.filternewsexample.service

import com.example.filternewsexample.model.ArticlesResponse
import io.reactivex.Observable

interface NewsAPIImpl {
    fun getNewsListTop(apiKey : String) : Observable<ArticlesResponse>
    fun getNewsListSearch(keySearch : String, apiKey : String) : Observable<ArticlesResponse>
}