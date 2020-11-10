package com.example.kotlinrnd.base.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.example.kotlinrnd.base.app.MyApplication
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CommonUtils {
    companion object {


        fun getDate(dateString: String?): String? {
            return try {
                val format1 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
                val date: Date = format1.parse(dateString)
                val sdf: DateFormat = SimpleDateFormat("MMM d, yyyy")
                sdf.format(date)
            } catch (ex: Exception) {
                ex.printStackTrace()
                "xx"
            }
        }

        fun getTime(dateString: String?): String? {
            return try {
                val format1 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
                val date: Date = format1.parse(dateString)
                val sdf: DateFormat = SimpleDateFormat("h:mm a")
                val netDate: Date = date
                sdf.format(netDate)
            } catch (ex: Exception) {
                ex.printStackTrace()
                "xx"
            }
        }

        fun getRandomNumber(): Long {
            return (Math.random() * (100000 - 0 + 1) + 0).toLong()
        }

    fun isConnectedToInternet(): Boolean {
        val mManager = MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (mManager != null) {
            val activeNetworkInfo = mManager.activeNetworkInfo
            return if (activeNetworkInfo != null && activeNetworkInfo.isAvailable && activeNetworkInfo.isConnected) {
                true
            } else {
                false
            }
        }
        return false
    }

        fun showToastMessage(msg: String?) {
            val toast = Toast.makeText(MyApplication.getAppContext(), msg, Toast.LENGTH_SHORT)
            toast.show()
        }
}
}