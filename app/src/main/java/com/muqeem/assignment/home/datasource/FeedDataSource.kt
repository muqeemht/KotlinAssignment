
package com.muqeem.assignment.home.datasource

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


class FeedDataSource(private val connectionError: MutableLiveData<Boolean>,  private val showHideLoading: MutableLiveData<Boolean>,

                     private val requestStatus: MutableLiveData<RequestStatus>

                     ) :PageKeyedDataSource<Long, NewsModel>() {

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, NewsModel>
    ) {
        val call: Call<NewsRSM> = RestClient.retrofitService.getNews(AppConstants.country, AppConstants.API_KEY, params.key, params.requestedLoadSize)
        call.enqueue(object : Callback<NewsRSM> {
            override fun onResponse(
                call: Call<NewsRSM>,
                response: Response<NewsRSM>
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
        val call: Call<NewsRSM> = RestClient.retrofitService.getNews(AppConstants.country, AppConstants.API_KEY, 1, params.requestedLoadSize)
        call.enqueue(object : Callback<NewsRSM> {
            override fun onResponse(
                call: Call<NewsRSM>,
                response: Response<NewsRSM>
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

            override fun onFailure(call: Call<NewsRSM>, t: Throwable) {
                requestStatus.setValue(NWRequestErrorUtil.createErrorResponse(t.message))
            }
        })
    }

}