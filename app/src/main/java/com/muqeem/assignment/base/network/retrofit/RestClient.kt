package com.muqeem.assignment.base.network.retrofit

import com.example.kotlinrnd.base.utils.CommonUtils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.muqeem.assignment.BuildConfig.BASE_URL
import com.muqeem.assignment.base.app.MyApplication
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


object RestClient {

    private fun retrofit(): Retrofit {

        val  HEADER_CACHE_CONTROL: String = "Cache-Control";
        val  HEADER_PRAGMA: String = "Pragma";

        val httpCacheDirectory = File(MyApplication.getAppContext().getCacheDir(), "responses")
        val cacheSize:Long = 10 * 1024 * 1024 // 10 MiB

        val cache = Cache(httpCacheDirectory, cacheSize)

        val builder = OkHttpClient.Builder().cache(cache).

        addNetworkInterceptor { chain ->
            var request = chain.request()
            request = if (!CommonUtils.isConnectedToInternet()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()

                request.newBuilder().cacheControl(cacheControl)
                    .build()
            } else {
                val cacheControl = CacheControl.Builder()
                    .maxAge(5, TimeUnit.SECONDS)
                    .build()

                request.newBuilder().cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }.

        addInterceptor { chain ->
            var request = chain.request()
            if(!CommonUtils.isConnectedToInternet()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()

                request = request.newBuilder().cacheControl(cacheControl)
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
            chain.proceed(request)
        }
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
