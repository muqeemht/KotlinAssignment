package com.muqeem.assignment.base.network.retrofit

import com.example.kotlinrnd.base.utils.CommonUtils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.muqeem.assignment.BuildConfig.BASE_URL
import com.muqeem.assignment.base.app.MyApplication
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


object RestClient {

    private fun retrofit(): Retrofit {




        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpCacheDirectory = File(MyApplication.getAppContext().getCacheDir(), "http-cache")
        val cacheSize:Long = 10 * 1024 * 1024 // 10 MiB

        val cache = Cache(httpCacheDirectory, cacheSize)

        val builder = OkHttpClient.Builder().cache(cache).
        addInterceptor(provideOfflineCacheInterceptor()).

        addNetworkInterceptor(networkCacheInterceptor).
        addInterceptor(loggingInterceptor)

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

    private val networkCacheInterceptor = Interceptor { chain ->


        val  HEADER_CACHE_CONTROL: String = "Cache-Control";
        val  HEADER_PRAGMA: String = "Pragma";

        val response: Response = chain.proceed(chain.request())

        val cacheControl: CacheControl

        cacheControl = if (CommonUtils.isConnectedToInternet()) {
            CacheControl.Builder()
                    .maxAge(0, TimeUnit.SECONDS)
                    .build()
        } else {
            CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
        }

        response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()



    }

    private fun provideOfflineCacheInterceptor(): Interceptor? {
        return Interceptor { chain ->
            var request = chain.request()
            val  HEADER_CACHE_CONTROL: String = "Cache-Control";
            val  HEADER_PRAGMA: String = "Pragma";
            if (!CommonUtils.isConnectedToInternet()) {
                val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()
                request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
            }
            chain.proceed(request)
        }
    }


    }
