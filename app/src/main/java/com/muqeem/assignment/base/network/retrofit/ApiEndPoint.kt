package com.muqeem.assignment.base.network.retrofit

import com.muqeem.assignment.home.models.NewsRSM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("top-headlines")
    open fun getNews(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?, @Query("page") page: Long?,
        @Query("pageSize") pageSize: Int?
    ): Call<NewsRSM>

    @GET("top-headlines")
    open fun getTopHeadlines(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?
    ): Call<NewsRSM>

}