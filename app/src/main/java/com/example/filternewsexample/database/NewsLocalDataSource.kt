package com.example.filternewsexample.database

import io.reactivex.Flowable

class NewsLocalDataSource(private val newsDao: NewsDAO) : NewsDataSource {

    override fun getAllNews(): Flowable<List<EntityNews>> {
        return newsDao.getAllNews()
    }

    override fun insertNews(news: EntityNews) {
        newsDao.insertNews(news)
    }

    override fun deleteNews(news: EntityNews) {
        newsDao.deleteNews(news)
    }

    override fun getAllFavorite(): Flowable<List<EntityFavorite>> {
        return newsDao.getAllFavourite()
    }

    override fun insertFavorite(newsFavorite: EntityFavorite) {
        newsDao.insertFavorite(newsFavorite)
    }

    override fun deleteFavorite(newsFavorite: EntityFavorite) {
        newsDao.deleteNewsFavorite(newsFavorite)
    }

    companion object {
        var instance : NewsLocalDataSource? = null
        fun getInstance(newsDao : NewsDAO) : NewsLocalDataSource {
            if (instance == null) {
                instance = NewsLocalDataSource(newsDao)
            }
            return instance as NewsLocalDataSource
        }
    }
}