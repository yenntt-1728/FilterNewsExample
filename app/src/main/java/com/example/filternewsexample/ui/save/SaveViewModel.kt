package com.example.filternewsexample.ui.save

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filternewsexample.database.AppDataBase
import com.example.filternewsexample.database.EntityNews
import com.example.filternewsexample.database.NewsLocalDataSource
import com.example.filternewsexample.database.NewsLocalRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SaveViewModel(private val context: Context) : ViewModel() {
    private lateinit var appDatabase: AppDataBase
    private lateinit var newsLocalRepository: NewsLocalRepository
    private var compositeDisposable = CompositeDisposable()
    val listNewsLiveData = MutableLiveData<List<EntityNews>>()

    fun getListSave(){
        appDatabase = AppDataBase.getInstance(context)
        newsLocalRepository = NewsLocalRepository.newInstance(NewsLocalDataSource.getInstance(appDatabase.newsDao()))
        val disposable: Disposable = newsLocalRepository.getAllNews()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
               listNewsLiveData.value = it
            },  {  })
        compositeDisposable.add(disposable)
    }
}
