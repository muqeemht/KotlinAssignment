package com.muqeem.assignment.base.network.retrofit

import com.muqeem.assignment.home.models.SourceRSM
import com.muqeem.assignment.home.models.NewsRSM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("everything")
    open fun getNews(
        @Query("q") country: String?,
        @Query("apiKey") apiKey: String?, @Query("page") page: Long?,
        @Query("pageSize") pageSize: Int?
    ): Call<NewsRSM>

    @GET("everything")
    suspend fun getNews2(
        @Query("q") country: String?,
        @Query("apiKey") apiKey: String?, @Query("page") page: Int?,
        @Query("pageSize") pageSize: Int?
    ): NewsRSM

    @GET("top-headlines")
    open fun getTopHeadlines(
        @Query("sources") country: String?,
        @Query("apiKey") apiKey: String?,
        @Query("page") page: Long?,
    @Query("pageSize") pageSize: Int?
    ): Call<NewsRSM>

    @GET("sources")
    open fun getNewsSources(
        @Query("apiKey") apiKey: String?
    ): Call<SourceRSM>

}