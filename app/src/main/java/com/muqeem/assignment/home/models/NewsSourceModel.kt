package com.muqeem.assignment.home.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsSourceModel{
    @SerializedName("id")
    @Expose
    var sourceID = ""

    @SerializedName("name")
    @Expose
    var sourceName = ""
}