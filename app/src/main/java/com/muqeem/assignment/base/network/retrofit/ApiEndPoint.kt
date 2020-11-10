package com.example.kotlinrnd.base.network.retrofit

import com.example.kotlinrnd.home.models.HeadlinesRSM
import com.example.kotlinrnd.login.models.LoginRSM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {
    @GET("top-headlines")
    open fun getNews(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?,   @Query("page") page: Long?,
        @Query("pageSize") pageSize: Int?
    ): Call<HeadlinesRSM>

    @GET("top-headlines")
    open fun getTopHeadlines(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): Call<HeadlinesRSM>
}