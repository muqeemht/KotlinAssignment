package com.muqeem.assignment.base.app

import android.app.Application
import android.content.Context
import com.muqeem.assignment.base.dagger.component.modules.AppComponent
import com.muqeem.assignment.base.dagger.component.modules.DaggerAppComponent

//import io.realm.Realm
//import io.realm.RealmConfiguration

class MyApplication : Application() {
    private  lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        appComponent.injectApplication(this)


       // appComponent = DaggerAppComponent.create()
//        Realm.init(this)
//        val config = RealmConfiguration.Builder().build()
//        Realm.setDefaultConfiguration(config)
        mContext = this
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }

    companion object {
        lateinit var mContext: Context
        fun getAppContext() : Context{
            return mContext
        }
    }
}
