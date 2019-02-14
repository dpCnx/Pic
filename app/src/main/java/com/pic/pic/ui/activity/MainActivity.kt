package com.pic.pic.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.pic.pic.R
import com.pic.pic.ui.fragment.PicFragment
import com.pic.pic.ui.fragment.Tvfragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val picFragment by lazy { PicFragment() }
    val tvfragment by lazy { Tvfragment() }

    var mList: ArrayList<Fragment>? = null
    var mTitle: Array<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        mList = ArrayList()
        mList?.add(picFragment)
        mList?.add(tvfragment)
        mTitle = resources.getStringArray(R.array.titles)

        mViewpager.adapter = myViewpagerAdater(supportFragmentManager)
        mTablayout.setupWithViewPager(mViewpager)
    }

    inner class myViewpagerAdater(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        override fun getItem(p0: Int): Fragment = mList?.get(p0) ?: picFragment

        override fun getCount(): Int = mList?.size ?: 0

        override fun getPageTitle(position: Int): CharSequence? = mTitle?.get(position)
    }
}
