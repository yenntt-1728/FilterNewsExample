package com.example.filternewsexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.filternewsexample.BR
import com.example.filternewsexample.R
import com.example.filternewsexample.adapter.SaveAdapter.SaveViewHolder
import com.example.filternewsexample.database.*
import com.example.filternewsexample.databinding.ItemNewsBinding
import com.example.filternewsexample.model.News
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class SaveAdapter(private val listNews: ArrayList<News>, private val context: Context) :
    RecyclerView.Adapter<SaveViewHolder>() {
    private var newsLocalRepository: NewsLocalRepository? = null
    private var appDatabase: AppDataBase? = null

    class SaveViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.setVariable(BR.viewModel, news)
            binding.executePendingBindings()
        }

        fun onLongClickItemSave(
            context: Context,
            newsLocalRepository: NewsLocalRepository,
            news: EntityNews
        ) {
            binding.itemNews.setOnLongClickListener {
                val popUpMenu = PopupMenu(context, it)
                popUpMenu.inflate(R.menu.menu_popup)
                popUpMenu.show()
                popUpMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.add_favorite -> {
                            newsLocalRepository.insertFavorite(
                                EntityFavorite(
                                    title = news.title,
                                    description = news.description,
                                    imageUrl = news.imageUrl
                                )
                            )
                            Toast.makeText(context, "Add To Favorite successfully", Toast.LENGTH_SHORT).show()
                        }
                        R.id.delete_download -> {
                            newsLocalRepository.deleteNews(news)
                        }
                    }
                    false
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val binding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_news, parent, false
        )
        return SaveViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        appDatabase = AppDataBase.getInstance(context)
        val news = listNews[position]
        holder.bind(news)
        appDatabase?.apply {
            newsLocalRepository =
                NewsLocalRepository.newInstance(NewsLocalDataSource.getInstance(this.newsDao()))
            newsLocalRepository?.apply {
                holder.onLongClickItemSave(
                    context, this, EntityNews(
                        title = news.title!!,
                        description = news.description,
                        imageUrl = news.urlToImage
                    )
                )
            }
        }
    }
}