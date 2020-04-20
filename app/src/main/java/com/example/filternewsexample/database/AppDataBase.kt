package com.example.filternewsexample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityNews::class, EntityFavorite::class], version = 2)

abstract class AppDataBase : RoomDatabase() {
    abstract fun newsDao() : NewsDAO

    companion object {
        fun getInstance(context: Context) : AppDataBase {
            val db = Room.databaseBuilder(context, AppDataBase::class.java, "news_database")
                .allowMainThreadQueries().build()
            return db
        }
    }
}