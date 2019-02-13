package com.pic.pic.http

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("/image/list/{pageNum}")
    fun getImageUrls(@Path("pageNum") pageNum: Int): Call<ResponseBody>
}