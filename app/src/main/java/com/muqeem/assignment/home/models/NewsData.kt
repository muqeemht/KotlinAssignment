
package com.muqeem.assignment.home.models
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class NewsData(@Expose val title: String?, @Expose val desc: String?, @Expose val url: String?) : Parcelable