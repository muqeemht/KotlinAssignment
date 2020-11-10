package com.muqeem.assignment.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.muqeem.assignment.base.constants.AppConstants
import com.muqeem.assignment.base.models.Meta
import com.muqeem.assignment.base.network.retrofit.RestClient
import com.example.kotlinrnd.base.utils.NWRequestErrorUtil
import com.example.kotlinrnd.base.viewmodels.BaseVewModel
import com.muqeem.assignment.home.datasource.NewsDataSourceFactory
import com.muqeem.assignment.home.models.NewsModel
import com.muqeem.assignment.home.models.NewsRSM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class NewsViewModel : BaseVewModel() {
    private var executor: Executor? = null

    private lateinit var allNews: LiveData<PagedList<NewsModel>>

    private val news: MutableLiveData<ArrayList<NewsModel>> by lazy {
        MutableLiveData<ArrayList<NewsModel>>()
    }

    fun onAllNewsReceived(): LiveData<PagedList<NewsModel>> {
       init()
        return allNews
    }

    fun onHeadlinesReceived(): LiveData<ArrayList<NewsModel>> {
        return news
    }

    private fun init() {
        executor = Executors.newFixedThreadPool(5)
        val feedDataFactory = NewsDataSourceFactory(connectionError, showHideLoading, requestStatus)

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()

        val pagedListBuilder: LivePagedListBuilder<Long, NewsModel>  = LivePagedListBuilder<Long, NewsModel>(feedDataFactory,
            pagedListConfig)



        allNews = pagedListBuilder.build()
    }


}



