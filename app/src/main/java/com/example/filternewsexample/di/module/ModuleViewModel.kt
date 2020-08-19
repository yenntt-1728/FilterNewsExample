package com.example.filternewsexample.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filternewsexample.di.util.ViewModelKey
import com.example.filternewsexample.ui.news.NewsViewModel
import com.example.filternewsexample.ui.save.SaveViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module()
 abstract class ModuleViewModel {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindListViewModel(newViewModel: NewsViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(SaveViewModel::class)
    abstract fun bindDetailsViewModel(saveViewModel: SaveViewModel?): ViewModel?

}