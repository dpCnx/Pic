package com.pic.pic.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.pic.pic.ui.BigPicActivity
import com.pic.pic.ui.MainActivity
import com.pic.pic.view.HomeitemView

class HomeAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mList: ArrayList<String> = ArrayList()

    fun initData(data: ArrayList<String>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()

    }

    fun addData(data: ArrayList<String>) {
        mList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        return HomeViewHolder(HomeitemView(p0.context))
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        var itemView = p0.itemView as HomeitemView
        itemView.setData(mList[p1])
        itemView.setOnClickListener {

            val intent = Intent(mainActivity, BigPicActivity::class.java)
            intent.putExtra("url", mList[p1])
            mainActivity.startActivity(intent)
        }
    }


    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}