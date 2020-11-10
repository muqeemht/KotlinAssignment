package com.example.kotlinrnd.base.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class MetaDataModel {

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("Meta")
    @Expose
    private var meta: Meta? = null

    open fun getMeta(): Meta? {
        return meta
    }

    open fun setMeta(meta: Meta?) {
        this.meta = meta
    }

}