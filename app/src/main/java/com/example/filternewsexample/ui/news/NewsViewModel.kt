package com.example.filternewsexample.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filternewsexample.model.News
import com.example.filternewsexample.service.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class NewsViewModel(private val newRepo : NewsRepository) : ViewModel() {
    private var listNews : List<News> = mutableListOf()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    val news =  MutableLiveData<List<News>>()

    fun getNews() : List<News>{
        val disposable : Disposable = newRepo.getNewsListTop(NewsFragment.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                news.value = it.articles
                listNews = it.articles
            }, {})
        compositeDisposable.add(disposable)
        return listNews
    }
}