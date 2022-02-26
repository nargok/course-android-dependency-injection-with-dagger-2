package com.techyourchance.dagger2course.common.dependencyinjection

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.screens.common.ScreenNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    @Provides
    fun screenNavigator() = ScreenNavigator(activity)

    @Provides
    fun application() = appCompositionRoot.application

    @Provides
    fun layoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    fun stackOverFlowApi() = appCompositionRoot.stackOverFlowApi

    @Provides
    fun activity() = activity
}