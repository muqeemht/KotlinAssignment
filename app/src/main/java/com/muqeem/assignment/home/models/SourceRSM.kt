


package com.muqeem.assignment.home.models
import com.muqeem.assignment.base.models.MetaDataModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SourceRSM : MetaDataModel(){
    @SerializedName("totalResult")
    @Expose
    var totalResults: Long? = null

    @SerializedName("sources")
    @Expose
    private var newsList: List<SourceModel>? = null

    fun getNewsList(): List<SourceModel>? {
        return newsList
    }



}