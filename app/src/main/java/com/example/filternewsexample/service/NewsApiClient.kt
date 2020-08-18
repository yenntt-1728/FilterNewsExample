package com.example.filternewsexample.service

import android.content.Context
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

open class NewsApiClient {
    @Provides
    @Singleton
    fun <T> createService(
        context: Context,
        endPoint: String?,
        serviceClass: Class<T>?): T {
        val httpClientBuilder = OkHttpClient.Builder()
        val cacheSize = 10 * 1024 * 1024
        httpClientBuilder.cache(
            Cache(
                context.applicationContext.cacheDir,
                cacheSize.toLong()
            )
        )
        val retrofit = Retrofit.Builder().baseUrl(endPoint)
            .client(httpClientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(serviceClass)
    }
}