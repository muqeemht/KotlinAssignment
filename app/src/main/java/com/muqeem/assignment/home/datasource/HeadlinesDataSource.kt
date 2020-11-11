
package com.muqeem.assignment.home.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.muqeem.assignment.base.constants.AppConstants
import com.muqeem.assignment.base.models.Meta
import com.muqeem.assignment.base.models.RequestStatus
import com.muqeem.assignment.base.network.retrofit.RestClient
import com.example.kotlinrnd.base.utils.NWRequestErrorUtil
import com.muqeem.assignment.home.models.NewsModel
import com.muqeem.assignment.home.models.NewsRSM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HeadlinesDataSource(private val connectionError: MutableLiveData<Boolean>, private val showHideLoading: MutableLiveData<Boolean>,

                          private val requestStatus: MutableLiveData<RequestStatus>, private val sourceName: String

                     ) :PageKeyedDataSource<Long, NewsModel>() {

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, NewsModel>
    ) {

        Log.i("TAG", "Loading Rang " + params.key + " Count " + params.requestedLoadSize);
        val call: Call<NewsRSM> = RestClient.retrofitService.getTopHeadlines(sourceName, AppConstants.API_KEY, params.key, params.requestedLoadSize)
        call.enqueue(object : Callback<NewsRSM> {
            override fun onResponse(
                call: Call<NewsRSM>,
                response: Response<NewsRSM>
            ) {
                try {
                    showHideLoading.postValue(false)

                    if (response.body() != null && !response.body().status.isNullOrEmpty()) {

                        val nexKey : Long? = if (params.key == response.body().totalResults) {
                            null

                        }else{
                            params.key+1
                        }

                        callback.onResult(response.body().getNewsList()!!, nexKey)
                    } else {
                      //  val meta: Meta = response.body().getMeta()!!
                        requestStatus.setValue(NWRequestErrorUtil.createErrorResponse("Data Loading error"))
                    }
                } catch (e: Exception) {
                    requestStatus.setValue(NWRequestErrorUtil.createErrorResponse(e.message))
                }
            }

            override fun onFailure(call: Call<NewsRSM>, t: Throwable) {
                requestStatus.setValue(NWRequestErrorUtil.createErrorResponse(t.message))
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, NewsModel?>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, NewsModel?>
    ) {
        val call: Call<NewsRSM> = RestClient.retrofitService.getTopHeadlines(sourceName, AppConstants.API_KEY, 1, params.requestedLoadSize)
        call.enqueue(object : Callback<NewsRSM> {
            override fun onResponse(
                call: Call<NewsRSM>,
                response: Response<NewsRSM>
            ) {
                try {
                    showHideLoading.postValue(false)

                    if (!response.body().status.isNullOrEmpty()) {
                        callback.onResult(response.body().getNewsList()!!, null, 2)
                    } else {
                        requestStatus.setValue(NWRequestErrorUtil.createErrorResponse("Error on data loading"))
                    }
                } catch (e: Exception) {
                    requestStatus.postValue(NWRequestErrorUtil.createErrorResponse(e.message))
                }
            }

            override fun onFailure(call: Call<NewsRSM>, t: Throwable) {
                requestStatus.postValue(NWRequestErrorUtil.createErrorResponse(t.message))
            }
        })
    }

}