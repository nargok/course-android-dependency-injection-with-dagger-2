package com.techyourchance.dagger2course.common.dependencyinjection.app

import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val application: MyApplication) {

    @AppScope
    @Provides
    fun retrofit(): Retrofit {
       return Retrofit.Builder()
           .baseUrl(Constants.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
    }

    @Provides
    fun application() = application

    @AppScope
    @Provides
    fun stackOverFlowApi(retrofit: Retrofit): StackoverflowApi = retrofit.create(StackoverflowApi::class.java)

}