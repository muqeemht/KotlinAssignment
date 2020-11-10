package com.muqeem.assignment.base.app

import android.app.Application
import android.content.Context
//import io.realm.Realm
//import io.realm.RealmConfiguration

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        Realm.init(this)
//        val config = RealmConfiguration.Builder().build()
//        Realm.setDefaultConfiguration(config)
        mContext = this
    }

    companion object {
        lateinit var mContext: Context
        fun getAppContext() : Context{
            return mContext
        }
    }
}
