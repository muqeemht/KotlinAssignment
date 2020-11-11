package com.muqeem.assignment.base.network.retrofit

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.muqeem.assignment.BuildConfig
import com.muqeem.assignment.base.app.MyApplication
import com.muqeem.assignment.base.dagger.modules.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @ApplicationScope
    fun provideRetrofit(): Retrofit {
        var context: Context = MyApplication.getAppContext()
        val cache = Cache(context.cacheDir!!, 5 * 1024 * 1024)
        val interceptor = HttpLoggingInterceptor()
        val okHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .cache(cache)
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
    fun provideInterceptor(): Interceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BASIC }
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
