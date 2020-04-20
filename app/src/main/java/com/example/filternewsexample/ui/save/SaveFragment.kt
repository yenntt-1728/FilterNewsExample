package com.example.filternewsexample.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.filternewsexample.R
import com.example.filternewsexample.adapter.SaveAdapter
import com.example.filternewsexample.database.EntityNews
import com.example.filternewsexample.model.News
import kotlinx.android.synthetic.main.fragment_save.*

class SaveFragment : Fragment() {
    private lateinit var viewModel: SaveViewModel
    private lateinit var saveAdapter: SaveAdapter
    private var listNews : ArrayList<News> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_save, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = SaveViewModel(context!!)
        viewModel.getListSave()
        viewModel.listNewsLiveData.observe(viewLifecycleOwner, Observer {
            val arrayListNews = arrayListOf<News>()
            for (item : EntityNews in it){
                val news = News(
                    title = item.title,
                    description = item.description,
                    urlToImage = item.imageUrl)
                arrayListNews.add(news)
            }
            addAllNews(arrayListNews)
            saveAdapter = SaveAdapter(listNews, context!!)
            recyclerSave.adapter = saveAdapter
        })
    }

    private fun addAllNews(arrayListNews : ArrayList<News>){
        listNews.clear()
        listNews.addAll(arrayListNews)
    }

    companion object {
        const val TAB_NAME = "SAVE"
        fun newInstance() = SaveFragment()
    }
}
