package com.muqeem.assignment.base.network.retrofit

import android.content.Context
import com.example.kotlinrnd.base.utils.CommonUtils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.muqeem.assignment.BuildConfig
import com.muqeem.assignment.base.app.MyApplication
import com.muqeem.assignment.base.dagger.modules.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @ApplicationScope
    fun provideRetrofit(): Retrofit {
        var context: Context = MyApplication.getAppContext()
        val okHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(provideOfflineCacheInterceptor())
                .addNetworkInterceptor(providenNetworkCacheInterceptor())
                .addInterceptor(provideLoggingInterceptor())
                .cache(provideCache(context))
                .build()
        return Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient =
            OkHttpClient
                    .Builder()
                    .addInterceptor(interceptor)
                    .cache(cache)
                    .build()
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Singleton
    @Provides
    fun provideOfflineCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val HEADER_CACHE_CONTROL: String = "Cache-Control";
            val HEADER_PRAGMA: String = "Pragma";
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

    @Singleton
    @Provides
    fun providenNetworkCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val  HEADER_CACHE_CONTROL: String = "Cache-Control";
            val  HEADER_PRAGMA: String = "Pragma";

            val response: Response = chain.proceed(chain.request())

            val cacheControl: CacheControl = if (CommonUtils.isConnectedToInternet()) {
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
    }
    @Singleton
    @Provides
    fun provideCache(context: Context): Cache = Cache(context.cacheDir, 5 * 1024 * 1024)
    @Singleton
    @Provides
    fun provideRxCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
    @Singleton
    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @ApplicationScope
    fun getApiInterface(retroFit: Retrofit): ApiEndPoint {
        return retroFit.create(ApiEndPoint::class.java)
    }

}
