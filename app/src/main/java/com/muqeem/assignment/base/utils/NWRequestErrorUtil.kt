package com.example.kotlinrnd.base.utils

import android.text.TextUtils
import com.example.kotlinrnd.R
import com.example.kotlinrnd.base.app.MyApplication
import com.example.kotlinrnd.base.models.Meta
import com.example.kotlinrnd.base.models.RequestStatus
import okhttp3.ResponseBody
import retrofit2.Call
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class NWRequestErrorUtil {
    fun createErrorResponse(message: String?): RequestStatus? {
        val value = RequestStatus()
        value.setError(true)
        value.setErrorMessage(message ?: "")
        return value
    }

    companion object {
        fun createErrorResponse(message: String?): RequestStatus? {
            val value = RequestStatus()
            value.setError(true)
            value.setErrorMessage(message ?: "")
            return value
        }
        fun createErrorResponse(meta: Meta?): RequestStatus? {
            return if (meta != null && meta.error && !TextUtils.isEmpty(meta.errorMessage)) {
                createErrorResponse(meta.errorMessage)
            } else createErrorResponse(MyApplication.getAppContext().getString(R.string.unknown_error))
        }

        fun createErrorResponse(call: Call<ResponseBody?>?, t: Throwable?): Boolean? {
            return if (call != null && !call.isCanceled && (t is ConnectException || t is SocketTimeoutException || t is SocketException || t is UnknownHostException)) {
                true
            } else false
        }

        fun createErrorResponse(t: Throwable?): Boolean? {
            return if (t is ConnectException || t is SocketTimeoutException || t is SocketException || t is UnknownHostException) {
                true
            } else false
        }
    }



}
