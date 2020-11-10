package com.example.kotlinrnd.base.app

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    companion object {
        lateinit var mContext: Context
        fun getAppContext() : Context{
            return mContext
        }
    }
}
