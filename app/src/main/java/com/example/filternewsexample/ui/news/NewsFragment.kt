package com.example.filternewsexample.ui.news

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.filternewsexample.R
import com.example.filternewsexample.adapter.NewsAdapter
import com.example.filternewsexample.service.NewsRepository
import kotlinx.android.synthetic.main.fragment_new.*

class NewsFragment : Fragment() {
    private lateinit var newViewModel: NewsViewModel
    private lateinit var newAdapter: NewsAdapter
    private lateinit var newsRepo: NewsRepository
    private lateinit var progressDialog : ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage(getString(R.string.message_loading))
        progressDialog.show()
        context?.apply {
            newsRepo = NewsRepository(this)
            newViewModel = NewsViewModel(newsRepo)
            newViewModel.getNews()
            newViewModel.news.observe(viewLifecycleOwner, Observer {
                newAdapter = NewsAdapter(newViewModel.news.value, this)
                recyclerNews.adapter = newAdapter
                progressDialog.dismiss()
            })
        }
    }

    companion object {
        const val API_KEY = "7257ad280bbe43a5a69295e4a85ea66a"
        const val TAB_NAME = "news"
        fun newInstance() = NewsFragment()
    }
}