
package com.muqeem.assignment.home.models
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsModel {

    @Transient
    private var id: Int = 0

    @SerializedName("title")
    @Expose
    var title = ""

    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null

    @SerializedName("description")
    @Expose
    var desc: String? = null



    constructor(){
        id = ++increment
    }


    companion object{
        var increment = 0
        var diffCallback: DiffUtil.ItemCallback<NewsModel> =
            object : DiffUtil.ItemCallback<NewsModel>() {
                override fun areItemsTheSame(
                        oldItem: NewsModel,
                        newItem: NewsModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                        oldItem: NewsModel,
                        newItem: NewsModel
                ): Boolean {
                    return oldItem == newItem
                }
            }

    }


}