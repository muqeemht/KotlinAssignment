package com.muqeem.assignment.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinrnd.base.utils.NWRequestErrorUtil
import com.example.kotlinrnd.base.viewmodels.BaseVewModel
import com.muqeem.assignment.base.app.MyApplication
import com.muqeem.assignment.base.constants.AppConstants
import com.muqeem.assignment.base.dagger.component.modules.AppComponent
import com.muqeem.assignment.base.dagger.component.modules.DaggerAppComponent
import com.muqeem.assignment.base.models.Meta
import com.muqeem.assignment.base.network.retrofit.ApiEndPoint
import com.muqeem.assignment.base.network.retrofit.RetrofitModule
import com.muqeem.assignment.home.models.SourceModel
import com.muqeem.assignment.home.models.SourceRSM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class SourceViewModel : BaseVewModel() {
    lateinit var apiInterface: ApiEndPoint

    private val sourcesList: MutableLiveData<List<SourceModel>> by lazy {
        MutableLiveData<List<SourceModel>>();
    }


    fun onSourcesReceived(): LiveData<List<SourceModel>> {
        getNewsSources()
        return sourcesList
    }


    private fun getNewsSources() {
       // if (!isNetworkConnected()) return
        showHideLoading.setValue(true)
        val call: Call<SourceRSM> = apiInterface.getNewsSources(AppConstants.API_KEY)
        call.enqueue(object : Callback<SourceRSM> {
            override fun onResponse(
                call: Call<SourceRSM>,
                response: Response<SourceRSM>
            ) {
                try {
                    showHideLoading.setValue(false)
                    if (!response.body().status.isNullOrEmpty()) {
                        sourcesList.setValue(response.body().getNewsList())
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




