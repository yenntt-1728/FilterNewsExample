package com.example.filternewsexample.database

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface NewsDAO {
    @Query("SELECT * FROM news")
    fun getAllNews() : Flowable<List<EntityNews>>

    @Query("SELECT * FROM favourite")
    fun getAllFavourite() : Flowable<List<EntityFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(vararg news : EntityNews)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(vararg entityFavorite: EntityFavorite)

    @Delete
    fun deleteNews(vararg news : EntityNews)

    @Delete
    fun deleteNewsFavorite(vararg newsFavorite: EntityFavorite)
}