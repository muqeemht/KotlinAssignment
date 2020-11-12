package com.muqeem.assignment.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinrnd.base.utils.NWRequestErrorUtil
import com.example.kotlinrnd.base.viewmodels.BaseVewModel
import com.muqeem.assignment.base.app.MyApplication
import com.muqeem.assignment.base.constants.AppConstants
import com.muqeem.assignment.home.models.SourceModel
import com.muqeem.assignment.home.models.SourceRSM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SourceViewModel : BaseVewModel() {
    private val sourcesList: MutableLiveData<List<SourceModel>> by lazy {
        MutableLiveData<List<SourceModel>>();
    }


    fun onSourcesReceived(): LiveData<List<SourceModel>> {
        getNewsSources()
        return sourcesList
    }


    private fun getNewsSources() {
       // if (!isNetworkConnected()) return
        var apiInterface = MyApplication.getAppComponent().getApiInterface()
        showHideLoading.setValue(true)
        val call: Call<SourceRSM> = apiInterface.getNewsSources(AppConstants.API_KEY)
        call.enqueue(object : Callback<SourceRSM> {
            override fun onResponse(
                call: Call<SourceRSM>,
                response: Response<SourceRSM>
            ) {
                try {
                    showHideLoading.postValue(false)
                    if (response.isSuccessful) {
                        sourcesList.setValue(response.body().getNewsList())
                    } else {
                        requestStatus.setValue(NWRequestErrorUtil.createErrorResponse(response.errorBody().string()))
                    }
                } catch (e: Exception) {
                    requestStatus.postValue(NWRequestErrorUtil.createErrorResponse(e.message))
                }
            }

            override fun onFailure(call: Call<SourceRSM>, t: Throwable) {
                requestStatus.postValue(NWRequestErrorUtil.createErrorResponse(t.message))
            }
        })
    }

}




