
package com.muqeem.assignment.home.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.kotlinrnd.base.utils.NWRequestErrorUtil
import com.muqeem.assignment.base.constants.AppConstants
import com.muqeem.assignment.base.models.Meta
import com.muqeem.assignment.base.models.RequestStatus
import com.muqeem.assignment.base.network.retrofit.RestClient
import com.muqeem.assignment.home.models.SourceModel
import com.muqeem.assignment.home.models.SourceRSM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SourceDataSource(private val connectionError: MutableLiveData<Boolean>, private val showHideLoading: MutableLiveData<Boolean>,

                       private val requestStatus: MutableLiveData<RequestStatus>

                     ) :PageKeyedDataSource<Long, SourceModel>() {

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, SourceModel>
    ) {
        val call: Call<SourceRSM> = RestClient.retrofitService.getNewsSources(AppConstants.API_KEY)
        call.enqueue(object : Callback<SourceRSM> {
            override fun onResponse(
                call: Call<SourceRSM>,
                response: Response<SourceRSM>
            ) {
                try {
                    showHideLoading.postValue(false)
                    val meta: Meta = response.body().getMeta()!!;
                    if (!meta.error) {

                        val nexKey : Long? = if (params.key == response.body().totalResults) {
                            null

                        }else{
                            params.key+1
                        }

                        callback.onResult(response.body().getNewsList()!!, nexKey)
                    } else {
                        requestStatus.setValue(NWRequestErrorUtil.createErrorResponse(meta))
                    }
                } catch (e: Exception) {
                    requestStatus.setValue(NWRequestErrorUtil.createErrorResponse(e.message))
                }
            }

            override fun onFailure(call: Call<SourceRSM>, t: Throwable) {
                requestStatus.setValue(NWRequestErrorUtil.createErrorResponse(t.message))
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, SourceModel?>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, SourceModel?>
    ) {
        val call: Call<SourceRSM> = RestClient.retrofitService.getTopHeadlines(AppConstants.country, AppConstants.API_KEY, 1, params.requestedLoadSize)
        call.enqueue(object : Callback<SourceRSM> {
            override fun onResponse(
                call: Call<SourceRSM>,
                response: Response<SourceRSM>
            ) {
                try {
                    showHideLoading.setValue(false)

                    if (!response.body().status.isNullOrEmpty()) {
                        callback.onResult(response.body().getNewsList()!!, null, 21)
                    } else {
                        val meta: Meta = response.body().getMeta()!!;
                        requestStatus.setValue(NWRequestErrorUtil.createErrorResponse(meta))
                    }
                } catch (e: Exception) {
                    requestStatus.setValue(NWRequestErrorUtil.createErrorResponse(e.message))
                }
            }

            override fun onFailure(call: Call<SourceRSM>, t: Throwable) {
                requestStatus.setValue(NWRequestErrorUtil.createErrorResponse(t.message))
            }
        })
    }

}