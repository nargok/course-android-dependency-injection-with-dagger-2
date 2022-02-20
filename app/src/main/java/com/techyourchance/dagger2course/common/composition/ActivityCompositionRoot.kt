package com.techyourchance.dagger2course.common.composition

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionDetailUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreenNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    // singletonにするため by lazyする
    val screenManager by lazy {
        ScreenNavigator(activity)
    }

    private val layoutInflater get() = LayoutInflater.from(activity)
    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)

    private val fragmentManager get() = activity.supportFragmentManager
    val dialogNavigator get() = DialogNavigator(fragmentManager)

    private val stackOverFlowApi: StackoverflowApi get() = appCompositionRoot.stackOverFlowApi

    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackOverFlowApi)
    val fetchQuestionDetailUseCase get() = FetchQuestionDetailUseCase(stackOverFlowApi)
}