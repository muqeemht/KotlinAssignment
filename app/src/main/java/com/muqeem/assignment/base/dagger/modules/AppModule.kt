package com.muqeem.assignment.base.dagger.modules

import com.muqeem.assignment.base.app.MyApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(myRetroApplication: MyApplication){

    var myRetroApplication:MyApplication

    init {
        this.myRetroApplication = myRetroApplication
    }

    @Provides
    fun provideMyRetroApplication():MyApplication{
        return myRetroApplication
    }
}