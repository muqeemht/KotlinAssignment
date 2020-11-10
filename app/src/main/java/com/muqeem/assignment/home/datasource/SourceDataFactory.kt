

package com.muqeem.assignment.home.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.muqeem.assignment.base.models.RequestStatus
import com.muqeem.assignment.home.models.SourceModel

class HeadlinesDataSourceFactory(private val connectionError: MutableLiveData<Boolean>,  private val showHideLoading: MutableLiveData<Boolean>,

                            private val requestStatus: MutableLiveData<RequestStatus>): DataSource.Factory<Long, SourceModel>() {
    val newsDataSourceLiveData = MutableLiveData<SourceDataSource>()

    override fun create(): DataSource<Long, SourceModel> {
        val newsDataSource = SourceDataSource(connectionError, showHideLoading, requestStatus)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}