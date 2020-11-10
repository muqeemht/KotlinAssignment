package com.muqeem.assignment.base.models

import com.google.gson.annotations.SerializedName

open class Meta {
    @SerializedName("Error")
    var error = false

    @SerializedName("ErrorMessage")
    var errorMessage: String? = null

}
