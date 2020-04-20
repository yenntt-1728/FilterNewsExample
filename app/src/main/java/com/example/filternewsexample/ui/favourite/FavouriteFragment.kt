package com.example.filternewsexample.ui.favourite

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.filternewsexample.R
import com.example.filternewsexample.adapter.FavoriteAdapter
import com.example.filternewsexample.adapter.SaveAdapter
import com.example.filternewsexample.database.EntityFavorite
import com.example.filternewsexample.database.EntityNews
import com.example.filternewsexample.model.News
import com.example.filternewsexample.ui.save.SaveViewModel
import kotlinx.android.synthetic.main.favourite_fragment.*
import kotlinx.android.synthetic.main.fragment_save.*

class FavouriteFragment : Fragment() {
    private lateinit var viewModel: FavouriteViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter
    private var listNews : ArrayList<News> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = FavouriteViewModel(context!!)
        viewModel.getListFavorite()
        viewModel.listNewsFavoriteLiveData.observe(viewLifecycleOwner, Observer {
            val arrayListNews = arrayListOf<News>()
            for (item : EntityFavorite in it){
                val news = News(
                    title = item.title,
                    description = item.description,
                    urlToImage = item.imageUrl)
                arrayListNews.add(news)
            }
            addAllNews(arrayListNews)
            favoriteAdapter = FavoriteAdapter(listNews, context!!)
            recyclerFavorite.adapter = favoriteAdapter
        })
    }

    private fun addAllNews(arrayListNews : ArrayList<News>){
        listNews.clear()
        listNews.addAll(arrayListNews)
    }

    companion object {
        const val TAB_NAME = "FAVOURITE"
        fun newInstance() = FavouriteFragment()
    }
}
