package com.example.filternewsexample.service

import android.content.Context
import com.example.filternewsexample.Utils.Constance

class NewsApiInstance : NewsApiClient() {
    private var newsAPI: NewsAPI? = null

    fun newInstance(context: Context) : NewsAPI {
        if (newsAPI == null){
            newsAPI = createService(context, Constance.BASE_URL, NewsAPI ::class.java)
        }
        return newsAPI as NewsAPI
    }

    companion object {
        fun getInstance() = NewsApiInstance()
    }
}