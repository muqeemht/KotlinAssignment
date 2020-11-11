package com.muqeem.assignment.base.dagger.component.modules

import android.content.Context
import com.muqeem.assignment.base.app.ApplicationContext
import com.muqeem.assignment.base.app.MyApplication
import com.muqeem.assignment.base.dagger.modules.ApplicationScope
import com.muqeem.assignment.base.dagger.modules.ContextModule
import com.muqeem.assignment.base.network.retrofit.ApiEndPoint
import com.muqeem.assignment.base.network.retrofit.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@ApplicationScope
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun getApiInterface(): ApiEndPoint
    fun injectApplication(myApplication: MyApplication)
}