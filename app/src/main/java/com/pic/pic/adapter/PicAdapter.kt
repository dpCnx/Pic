package com.pic.pic.adapter

import com.pic.pic.base.BaseItemView
import com.pic.pic.base.BaseListAdapter

class PicAdapter : BaseListAdapter<String>() {

    override fun reflashItemView(itemview: BaseItemView, d: String) {
        itemview.setData(d)
    }

}