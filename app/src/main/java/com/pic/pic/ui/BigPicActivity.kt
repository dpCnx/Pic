package com.pic.pic.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.pic.pic.R
import com.pic.pic.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_big_pic.*


class BigPicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_big_pic)

        val url = intent.getStringExtra("url")


        GlideUtils.setBitmap(this, url, photo_view)

        photo_view.setOnClickListener {
            finish()
        }
    }
}
