


package com.muqeem.assignment.home.models
import com.muqeem.assignment.base.models.MetaDataModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsRSM : MetaDataModel(){
    @SerializedName("totalResults")
    @Expose
    var totalResults: Long? = null

    @SerializedName("articles")
    @Expose
    private var newsList: List<NewsModel>? = null

    fun getNewsList(): List<NewsModel>? {
        return newsList
    }



}