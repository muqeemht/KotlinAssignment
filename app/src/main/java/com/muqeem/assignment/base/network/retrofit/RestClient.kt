package com.muqeem.assignment.base.network.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    private fun retrofit(): Retrofit {
        val builder = OkHttpClient.Builder()
        val clientBuilder = builder.build()
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder)
                .baseUrl(BASE_URL)
                .build()
    }

    val retrofitService: ApiEndPoint by lazy {
        retrofit().create(ApiEndPoint::class.java)
    }

    }
