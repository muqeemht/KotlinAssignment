package com.muqeem.assignment.base.app

import android.app.Application
import android.content.Context
import com.muqeem.assignment.base.dagger.component.modules.AppComponent
import com.muqeem.assignment.base.dagger.component.modules.DaggerAppComponent
import com.muqeem.assignment.base.network.retrofit.RetrofitModule

//import io.realm.Realm
//import io.realm.RealmConfiguration

class MyApplication : Application() {
    private  lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        mContext = this
        appComponent = DaggerAppComponent.builder().retrofitModule(RetrofitModule()).build()
        appComponent.injectApplication(this)

//        Realm.init(this)
//        val config = RealmConfiguration.Builder().build()
//        Realm.setDefaultConfiguration(config)

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
