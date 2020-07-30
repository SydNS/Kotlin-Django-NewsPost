package com.example.UTAMU.FragmentsJavaClasses.ForTheBottomNav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterViewFlipper
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.UTAMU.R
import com.google.android.material.tabs.TabLayout

class SecondFragment : Fragment() {
    var newsFlipper: AdapterViewFlipper? = null
    var newsTab1: ViewPager? = null
    var TabCategoriesViewPager: ViewPager? = null
    var TabNews1: TabLayout? = null
    var TabCategories: TabLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag2, container, false)
        val tabVieWPagers = TabVieWPagers(childFragmentManager)
        newsFlipper = view.findViewById<View>(R.id.newsFlipper) as AdapterViewFlipper
        TabNews1 = view.findViewById<View>(R.id.newTab1) as TabLayout
        TabCategories = view.findViewById<View>(R.id.TabCategories) as TabLayout
        newsTab1 = view.findViewById<View>(R.id.newTab1ViewPager) as ViewPager
        TabCategoriesViewPager =
            view.findViewById<View>(R.id.TabCategoriesViewPager) as ViewPager
        newsTab1!!.adapter = tabVieWPagers
        TabNews1!!.setupWithViewPager(newsTab1)
        return view
    }
}