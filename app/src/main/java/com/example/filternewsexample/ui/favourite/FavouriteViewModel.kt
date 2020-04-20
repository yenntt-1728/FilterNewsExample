package com.example.filternewsexample.ui.favourite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filternewsexample.database.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavouriteViewModel(private val context: Context) : ViewModel() {
    private lateinit var appDatabase: AppDataBase
    private lateinit var newsLocalRepository: NewsLocalRepository
    private var compositeDisposable = CompositeDisposable()
    val listNewsFavoriteLiveData = MutableLiveData<List<EntityFavorite>>()

    fun getListFavorite(){
        appDatabase = AppDataBase.getInstance(context)
        newsLocalRepository = NewsLocalRepository.newInstance(NewsLocalDataSource.getInstance(appDatabase.newsDao()))
        val disposable: Disposable = newsLocalRepository.getAllFavorite()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                listNewsFavoriteLiveData.value = it
            },  {  })
        compositeDisposable.add(disposable)
    }
}
