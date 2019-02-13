package com.pic.pic.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.pic.pic.R
import com.pic.pic.utils.GlideUtils
import kotlinx.android.synthetic.main.adapter_home_item.view.*


class HomeitemView : RelativeLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.adapter_home_item, this)

    }

    fun setData(s: String) {
        GlideUtils.setBitmap(context, s,mImageView)
    }
}