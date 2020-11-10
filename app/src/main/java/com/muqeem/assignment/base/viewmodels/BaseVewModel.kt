package com.example.kotlinrnd.base.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muqeem.assignment.base.models.RequestStatus
import com.example.kotlinrnd.base.utils.CommonUtils

open class BaseVewModel : ViewModel(){

    val connectionError: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val showHideLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val requestStatus: MutableLiveData<RequestStatus> by lazy {
        MutableLiveData<RequestStatus>()
    }

    fun isNetworkConnected(): Boolean {
        val flag: Boolean = CommonUtils.isConnectedToInternet()
        if (!flag) connectionError.setValue(true)
        return flag
    }




}