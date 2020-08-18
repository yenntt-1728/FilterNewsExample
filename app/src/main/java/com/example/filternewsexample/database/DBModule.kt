package com.example.filternewsexample.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @class DBModule
 * Created by SUN-ASTERISK\nguyen.thi.tu.yen on 18,August,2020
 */

@Module
class DBModule {
    @Provides
    @Singleton
    internal fun provideDataBase(context: Context): AppDataBase {
        val db = Room.databaseBuilder(context, AppDataBase::class.java, "news_database")
            .allowMainThreadQueries().build()
        return db
    }

    @Provides
    @Singleton
    internal fun provideNewDao(appDataBase: AppDataBase): NewsDAO {
        return appDataBase.newsDao()
    }
}