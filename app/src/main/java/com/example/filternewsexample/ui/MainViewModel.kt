package com.example.filternewsexample.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filternewsexample.model.News
import com.example.filternewsexample.service.NewsRepository
import com.example.filternewsexample.ui.news.NewsFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val newRepo: NewsRepository) : ViewModel() {
    val listNewsSearch = MutableLiveData<List<News>>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    fun getListSearchNew(key: String) {
        showLoading()
        compositeDisposable.add(newRepo.getNewsListSearch(key, NewsFragment.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                listNewsSearch.value = it.articles
            })
    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }
}