package com.example.kotlinrnd.home.models

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator


class Source protected constructor(`in`: Parcel) : Parcelable {
    var name: String?

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Creator<Source> = object : Creator<Source> {
            override fun createFromParcel(`in`: Parcel): Source? {
                return Source(`in`)
            }

            override fun newArray(size: Int): Array<Source?> {
                return arrayOfNulls(size)
            }
        }
    }

    init {
        name = `in`.readString()
    }
}