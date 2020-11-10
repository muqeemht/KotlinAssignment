
package com.muqeem.assignment.home.models
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SourceModel {

    @Transient
    private var id: Int = 0

    @SerializedName("id")
    @Expose
    var source = ""

    @SerializedName("name")
    @Expose
    var name = ""

    @SerializedName("description")
    @Expose
    var desc: String? = null

    constructor(){
        id = ++increment
    }


    companion object{
        var increment = 0
        var diffCallback: DiffUtil.ItemCallback<SourceModel> =
            object : DiffUtil.ItemCallback<SourceModel>() {
                override fun areItemsTheSame(
                    oldItem: SourceModel,
                    newItem: SourceModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: SourceModel,
                    newItem: SourceModel
                ): Boolean {
                    return oldItem == newItem
                }
            }

    }


}