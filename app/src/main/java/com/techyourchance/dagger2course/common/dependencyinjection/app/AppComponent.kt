package com.techyourchance.dagger2course.common.dependencyinjection.app

import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun application(): MyApplication

    fun stackOverFlowApi(): StackoverflowApi

}