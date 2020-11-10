package com.example.kotlinrnd.base.network.retrofit

import com.example.kotlinrnd.BuildConfig
import com.example.kotlinrnd.BuildConfig.BASE_URL
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
