package com.example.filternewsexample.di.component

import android.app.Application
import com.example.filternewsexample.di.module.ApplicationModule
import com.example.filternewsexample.di.module.ContextModule
import com.example.filternewsexample.di.module.ModuleViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication as DaggerApplication

@Component(modules = [ApplicationModule::class, ContextModule::class, ModuleViewModel::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication>{
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): ApplicationComponent?
    }
}