

package com.muqeem.assignment.home.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.muqeem.assignment.base.models.RequestStatus
import com.muqeem.assignment.home.models.NewsModel

class NewsDataSourceFactory(private val connectionError: MutableLiveData<Boolean>,  private val showHideLoading: MutableLiveData<Boolean>,

                            private val requestStatus: MutableLiveData<RequestStatus>): DataSource.Factory<Long, NewsModel>() {
    val newsDataSourceLiveData = MutableLiveData<FeedDataSource>()

    override fun create(): DataSource<Long, NewsModel> {
        val newsDataSource = FeedDataSource(connectionError, showHideLoading, requestStatus)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}