package com.pic.pic.base

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pic.pic.R
import kotlinx.android.synthetic.main.fragment_pic.*

abstract class BaseListFragment<D> : BaseFragment() {

    val mAdater by lazy { getBaseListAdapter() }

    protected var pageInt = 0
    protected var isAddmore = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(context, R.layout.fragment_pic, null)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initAdapter()
        initData(pageInt)
        initLisener()

        mSwipeRefreshLayout.setOnRefreshListener {
            pageInt = 0
            isAddmore = false
            initData(pageInt)
        }
    }

    private fun initAdapter() {
        var mManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mRecyclerView.layoutManager = mManager
        mRecyclerView.adapter = mAdater
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
                    val array = IntArray(layoutManager.spanCount)
                    val findLastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(array)
                    val findMax = findMax(findLastVisibleItemPositions)
                    if (findMax == layoutManager.itemCount - 1) {
                        pageInt += 1
                        isAddmore = true
                        initData(pageInt)
                    }
                }
            }
        })

    }

    private fun findMax(findLastVisibleItemPositions: IntArray): Int {
        var max = findLastVisibleItemPositions[0]
        findLastVisibleItemPositions.forEach {
            if (it > max) {
                max = it
            }
        }
        return max
    }

    abstract fun getBaseListAdapter(): BaseListAdapter<D>
    abstract fun initLisener()
    abstract fun initData(pageInt: Int)
}