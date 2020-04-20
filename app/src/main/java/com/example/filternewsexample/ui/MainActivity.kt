package com.example.filternewsexample.ui

import android.app.ProgressDialog
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import com.example.filternewsexample.R
import com.example.filternewsexample.adapter.MainPagerAdapter
import com.example.filternewsexample.adapter.NewsAdapter
import com.example.filternewsexample.service.NewsRepository
import com.example.filternewsexample.ui.favourite.FavouriteFragment
import com.example.filternewsexample.ui.news.NewsFragment
import com.example.filternewsexample.ui.save.SaveFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_new.*

const val PERMISSION_REQUEST_CODE = 1234
class MainActivity : AppCompatActivity(){
    private lateinit var searchView : SearchView
    private lateinit var searchAdapter : NewsAdapter
    private lateinit var mainViewModel : MainViewModel
    private lateinit var newRepo : NewsRepository
    private lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.item_search)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        setUpTab()
//        setUpPermission()
        newRepo = NewsRepository(this)
        mainViewModel = MainViewModel(newRepo)
    }

    private fun setUpTab(){
        val tab: MutableList<Pair<Fragment, String>> = mutableListOf(
            Pair(NewsFragment.newInstance(), NewsFragment.TAB_NAME),
            Pair(SaveFragment.newInstance(), SaveFragment.TAB_NAME),
            Pair(FavouriteFragment.newInstance(), FavouriteFragment.TAB_NAME)
        )
        val mainPagerAdapter = MainPagerAdapter(supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT).apply {
            setListItem(tab)
        }
        viewPager.adapter = mainPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val searchManager : SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.item_search, menu)
        searchView = menu?.findItem(R.id.item_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                mainViewModel.getListSearchNew(p0.toString())
                progressDialog.show()
                mainViewModel.listNewsSearch.observe(this@MainActivity, Observer {
                    searchAdapter = NewsAdapter(mainViewModel.listNewsSearch.value, applicationContext)
                    recyclerNews.adapter = searchAdapter
                    progressDialog.dismiss()
                })
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        return true
    }
}
