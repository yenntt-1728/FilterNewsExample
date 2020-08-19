package com.example.filternewsexample.di.module

import com.example.filternewsexample.service.NewsAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {
    private val BASE_URL = "https://api.github.com/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit? {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): NewsAPI? {
        return retrofit.create<NewsAPI>(NewsAPI::class.java)
    }
}