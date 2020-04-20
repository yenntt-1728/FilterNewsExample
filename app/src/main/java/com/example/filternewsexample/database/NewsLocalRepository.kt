package com.example.filternewsexample.database

import io.reactivex.Flowable

class NewsLocalRepository(private val newsDataSource: NewsDataSource) : NewsDataSource {

    override fun getAllNews(): Flowable<List<EntityNews>> {
        return newsDataSource.getAllNews()
    }

    override fun insertNews(news: EntityNews) {
        newsDataSource.insertNews(news)
    }

    override fun deleteNews(news: EntityNews) {
        newsDataSource.deleteNews(news)
    }

    override fun getAllFavorite(): Flowable<List<EntityFavorite>> {
        return newsDataSource.getAllFavorite()
    }

    override fun insertFavorite(newsFavorite: EntityFavorite) {
        newsDataSource.insertFavorite(newsFavorite)
    }

    override fun deleteFavorite(newsFavorite: EntityFavorite) {
        newsDataSource.deleteFavorite(newsFavorite)
    }

    companion object {
        fun newInstance(newsDataSource: NewsDataSource) : NewsLocalRepository{
            var newDataRepo : NewsLocalRepository? = null
            if (newDataRepo == null){
                newDataRepo = NewsLocalRepository(newsDataSource)
            }
            return newDataRepo
        }
    }
}