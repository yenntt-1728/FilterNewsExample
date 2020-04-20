package com.example.filternewsexample.adapter

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.filternewsexample.BR
import com.example.filternewsexample.R
import com.example.filternewsexample.database.AppDataBase
import com.example.filternewsexample.database.EntityNews
import com.example.filternewsexample.database.NewsLocalDataSource
import com.example.filternewsexample.database.NewsLocalRepository
import com.example.filternewsexample.databinding.ItemNewsBinding
import com.example.filternewsexample.model.News

class NewsAdapter(private val listNews : List<News>?, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var newsLocalRepository : NewsLocalRepository? = null
    private var appDatabase : AppDataBase? = null

    class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(new : News){
            binding.setVariable(BR.viewModel, new)
            binding.executePendingBindings()
        }

        fun openUrl(context: Context, new: News){
            binding.itemNews.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(new.url))
                browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(browserIntent)
            }
        }

        fun onLongClickItem(context: Context, news: News, newsLocalRepository: NewsLocalRepository, newsEntity: EntityNews){
            binding.itemNews.setOnLongClickListener{
                saveNews(news, context)
                newsLocalRepository.insertNews(newsEntity)
                true
            }
        }

        private fun saveNews(news : News , context: Context) {
            val request: DownloadManager.Request = DownloadManager.Request(Uri.parse(news.urlToImage))
            request.setTitle(news.title)
            request.setDescription(news.description)
            request.setDestinationUri(Uri.parse(news.urlToImage))
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                request.allowScanningByMediaScanner()
                request.setNotificationVisibility(
                    DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
                )
            }
            request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "news" + ".ext")
            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
            Toast.makeText(context, "Download successfully", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding : ItemNewsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_news, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
       if (listNews?.size == null) return 0
        return listNews.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        appDatabase = AppDataBase.getInstance(context)
        if (listNews?.size != null) {
            val news = listNews[position]
            holder.bind(news)
            holder.openUrl(context, news)
            appDatabase?.apply {
                newsLocalRepository = NewsLocalRepository.newInstance(NewsLocalDataSource.getInstance(this.newsDao()))
                newsLocalRepository?.apply {
                    holder.onLongClickItem(context, listNews[position], this,
                        EntityNews(
                            title = news.title!!,
                            description = news.description,
                            imageUrl = news.urlToImage))
                }
            }
        }

    }
}