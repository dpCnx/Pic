package com.pic.pic.ui.fragment

import android.content.Intent
import android.util.Log
import com.pic.pic.adapter.PicAdapter
import com.pic.pic.base.BaseListAdapter
import com.pic.pic.base.BaseListFragment
import com.pic.pic.http.RetrofitClient
import com.pic.pic.ui.activity.BigPicActivity
import kotlinx.android.synthetic.main.fragment_pic.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PicFragment : BaseListFragment<String>() {

    override fun getBaseListAdapter(): BaseListAdapter<String> = PicAdapter()

    override fun initLisener() {
        mAdater.setMyLisener {
            val intent = Intent(context, BigPicActivity::class.java)
            intent.putExtra("url", it)
            context?.startActivity(intent)
        }
    }

    override fun initData(pageInt: Int) {

        mSwipeRefreshLayout.isRefreshing = true
        val call = RetrofitClient.retrofitClient.getApiServer()?.getImageUrls(pageInt)
        call?.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.e("tag", t?.message)
                mSwipeRefreshLayout.isRefreshing = false
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {

                activity?.runOnUiThread {
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