

package com.muqeem.assignment.home.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.muqeem.assignment.base.models.RequestStatus
import com.muqeem.assignment.home.models.NewsModel

class HeadlinesDataFactory(private val connectionError: MutableLiveData<Boolean>,  private val showHideLoading: MutableLiveData<Boolean>,

                            private val requestStatus: MutableLiveData<RequestStatus>, private val sourceName: String): DataSource.Factory<Long, NewsModel>() {
    val newsDataSourceLiveData = MutableLiveData<HeadlinesDataSource>()

    override fun create(): DataSource<Long, NewsModel> {
        val newsDataSource = HeadlinesDataSource(connectionError, showHideLoading, requestStatus, sourceName)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}