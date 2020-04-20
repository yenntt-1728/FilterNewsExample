package com.example.filternewsexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.filternewsexample.BR
import com.example.filternewsexample.R
import com.example.filternewsexample.database.AppDataBase
import com.example.filternewsexample.database.EntityFavorite
import com.example.filternewsexample.database.NewsLocalDataSource
import com.example.filternewsexample.database.NewsLocalRepository
import com.example.filternewsexample.databinding.ItemNewsBinding
import com.example.filternewsexample.model.News

class FavoriteAdapter(private val listNews : ArrayList<News>, private val context : Context) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private lateinit var appDataBase : AppDataBase
    private lateinit var newsLocalRepo : NewsLocalRepository

    class FavoriteViewHolder(private val binding : ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news : News){
            binding.setVariable(BR.viewModel, news)
            binding.executePendingBindings()
        }

        fun onLongClickItemSave(context: Context, newsLocalRepository: NewsLocalRepository, newsFavorite : EntityFavorite){
            binding.itemNews.setOnLongClickListener{
                val popUpMenu = PopupMenu(context,binding.itemNews)
                popUpMenu.inflate(R.menu.menu_popup_favorite)
                popUpMenu.show()
                popUpMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.delete_favorite -> {
                            newsLocalRepository.deleteFavorite(newsFavorite)
                        }
                    }
                    false
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding : ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_news, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val news = listNews[position]
        holder.bind(listNews[position])
        appDataBase = AppDataBase.getInstance(context)
        newsLocalRepo = NewsLocalRepository.newInstance(NewsLocalDataSource.getInstance(appDataBase.newsDao()))
        holder.onLongClickItemSave(context, newsLocalRepo, EntityFavorite(title = news.title!!,
            description = news.description, imageUrl = news.urlToImage)
        )
    }
}