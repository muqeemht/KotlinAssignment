package com.muqeem.assignment.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.kotlinrnd.base.viewmodels.BaseVewModel
import com.muqeem.assignment.home.datasource.HeadlinesDataFactory
import com.muqeem.assignment.home.datasource.NewsDataSourceFactory
import com.muqeem.assignment.home.models.NewsModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class NewsViewModel : BaseVewModel() {
    private var executor: Executor? = null

    private lateinit var allNews: LiveData<PagedList<NewsModel>>
    private lateinit var headLines: LiveData<PagedList<NewsModel>>


    fun onAllNewsReceived(): LiveData<PagedList<NewsModel>> {
       init()
        return allNews
    }

    fun onHeadlinesReceived(sourceName:String): LiveData<PagedList<NewsModel>> {
        initHeadLines(sourceName)
        return headLines
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


    private fun initHeadLines(sourceName: String) {
        executor = Executors.newFixedThreadPool(5)
        val feedDataFactory = HeadlinesDataFactory(connectionError, showHideLoading, requestStatus, sourceName)

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()

        val pagedListBuilder: LivePagedListBuilder<Long, NewsModel>  = LivePagedListBuilder<Long, NewsModel>(feedDataFactory,
            pagedListConfig)



        headLines = pagedListBuilder.build()
    }






}




