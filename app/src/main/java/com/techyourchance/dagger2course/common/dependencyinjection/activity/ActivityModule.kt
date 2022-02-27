package com.techyourchance.dagger2course.common.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.common.dependencyinjection.app.AppComponent
import com.techyourchance.dagger2course.screens.common.ScreenNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    private val activity: AppCompatActivity,
    private val appComponent: AppComponent
) {

    @ActivityScope
    @Provides
    fun screenNavigator(activity: AppCompatActivity) = ScreenNavigator(activity)

    @Provides
    fun application() = appComponent.application()

    @Provides
    fun layoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    fun stackOverFlowApi() = appComponent.stackOverFlowApi()

    @Provides
    fun activity() = activity
}