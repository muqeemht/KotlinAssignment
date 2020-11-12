package com.muqeem.assignment.base.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.muqeem.assignment.base.models.Meta

open class MetaDataModel {

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null


}