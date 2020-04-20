package com.example.filternewsexample.database

import io.reactivex.Flowable

interface NewsDataSource {
    fun getAllNews() : Flowable<List<EntityNews>>

    fun insertNews(news : EntityNews)

    fun deleteNews(news : EntityNews)

    fun getAllFavorite() : Flowable<List<EntityFavorite>>

    fun insertFavorite(newsFavorite: EntityFavorite)

    fun deleteFavorite(newsFavorite: EntityFavorite)

}