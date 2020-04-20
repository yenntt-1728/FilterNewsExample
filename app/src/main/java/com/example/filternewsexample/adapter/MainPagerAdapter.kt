package com.example.filternewsexample.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm : FragmentManager, behavior : Int) : FragmentPagerAdapter(fm, behavior){
    private var listItem : MutableList<Pair<Fragment, String>> = mutableListOf()

    override fun getCount(): Int {
        return listItem.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listItem[position].second
    }

    override fun getItem(position: Int): Fragment {
        return listItem[position].first
    }

    fun setListItem(listItem : MutableList<Pair<Fragment, String>>){
        this.listItem = listItem
    }
}