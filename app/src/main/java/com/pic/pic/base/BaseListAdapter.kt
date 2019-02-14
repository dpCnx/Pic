package com.pic.pic.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

abstract class BaseListAdapter<D> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mList: ArrayList<D> = ArrayList()

    private var lisener: ((data: D) -> Unit)? = null

    fun setMyLisener(listener: (data: D) -> Unit) {
        this.lisener = listener
    }

    fun initData(data: ArrayList<D>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()

    }

    fun addData(data: ArrayList<D>) {
        mList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        return BaseListViewHolder(BaseItemView(p0.context))
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        var itemView = p0.itemView as BaseItemView
        reflashItemView(itemView, mList[p1])
        itemView.setOnClickListener {

            lisener?.let {
                it.invoke(mList[p1])
            }
        }
    }

    inner class BaseListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    abstract fun reflashItemView(itemview: BaseItemView, d: D)
}