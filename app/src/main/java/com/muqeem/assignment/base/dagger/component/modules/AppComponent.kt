package com.muqeem.assignment.base.dagger.component.modules

import com.muqeem.assignment.base.app.MyApplication
import com.muqeem.assignment.base.dagger.modules.AppModule
import com.muqeem.assignment.base.dagger.modules.ApplicationScope
import com.muqeem.assignment.base.network.retrofit.ApiEndPoint
import com.muqeem.assignment.base.network.retrofit.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@ApplicationScope
@Component(modules = [AppModule::class, RetrofitModule::class])
interface AppComponent {
    fun getApiInterface(): ApiEndPoint
    fun inject(retrofitRepository: ApiEndPoint)
    fun injectApplication(myApplication: MyApplication)
}