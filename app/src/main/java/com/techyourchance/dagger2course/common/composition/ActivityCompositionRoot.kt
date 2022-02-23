package com.techyourchance.dagger2course.common.composition

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.ScreenNavigator

class ActivityCompositionRoot(
    val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    // singletonにするため by lazyする
    val screenManager by lazy {
        ScreenNavigator(activity)
    }

    val application get() = appCompositionRoot.application

    val layoutInflater: LayoutInflater get() = LayoutInflater.from(activity)
    val fragmentManager get() = activity.supportFragmentManager
    val stackOverFlowApi: StackoverflowApi get() = appCompositionRoot.stackOverFlowApi

}