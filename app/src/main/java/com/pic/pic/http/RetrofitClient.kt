package com.pic.pic.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {

    private var retrofitService: RetrofitService? = null

    companion object {

        val retrofitClient by lazy { RetrofitClient() }
    }

    init {

        val ocb = OkHttpClient.Builder()
                .connectTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(45, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://106.12.40.96:8111")
                .client(ocb)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofitService = retrofit.create(RetrofitService::class.java)
    }

    fun getApiServer(): RetrofitService? {
        return retrofitService
    }
}
