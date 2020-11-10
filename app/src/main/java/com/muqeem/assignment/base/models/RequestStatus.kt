package com.example.kotlinrnd.base.models

class RequestStatus{
    private var isError = false
    private var errorMessage: String? = null


    fun isError(): Boolean {
        return isError
    }

    fun setError(error: Boolean) {
        isError = error
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }

    fun setErrorMessage(errorMessage: String?) {
        this.errorMessage = errorMessage
    }
}