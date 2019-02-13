package com.pic.pic.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.pic.pic.R
import com.pic.pic.adapter.HomeAdapter
import com.pic.pic.http.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var mAdater: HomeAdapter? = null
    private var pageInt = 0
    private var isAddmore = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        initAdapter()
        initData(pageInt)

        mSwipeRefreshLayout.setOnRefreshListener {
            pageInt = 0
            isAddmore = false
            initData(pageInt)
        }
    }

    private fun initAdapter() {
        mAdater = HomeAdapter(this)
        var mManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        var mManager = GridLayoutManager(this, 2)
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

//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
//
//                    if (layoutManager.findLastVisibleItemPosition() == mAdater!!.itemCount - 1) {
//                        pageInt += 1
//                        isAddmore = true
//                        initData(pageInt)
//                    }
//
//
//                }
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

    private fun initData(pageInt: Int) {

        mSwipeRefreshLayout.isRefreshing = true

        val call = RetrofitClient.retrofitClient.getApiServer()?.getImageUrls(pageInt)

        call?.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.e("tag", t?.message)
                mSwipeRefreshLayout.isRefreshing = false
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                runOnUiThread {
                    mSwipeRefreshLayout.isRefreshing = false
                    response?.let {
                        if (it.isSuccessful) {
                            val res = it.body()?.string()
                            val json = JSONObject(res)
                            val urls = json.optJSONArray("urls")
                            val mList = ArrayList<String>()
                            for (i in 0 until urls.length()) {
                                mList.add(urls.optString(i))
                            }
                            if (!isAddmore) {
                                mAdater?.initData(mList)
                            } else {
                                mAdater?.addData(mList)
                            }
                        }
                    }
                }
            }
        })
    }
}
