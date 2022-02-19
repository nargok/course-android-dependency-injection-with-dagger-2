package com.techyourchance.dagger2course.common.composition

import android.app.Activity
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionDetailUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreenNavigator

class ActivityCompositionRoot(
    private val activity: Activity,
    private val appCompositionRoot: AppCompositionRoot
) {

    // singletonにするため by lazyする
    val screenManager by lazy {
        ScreenNavigator(activity)
    }

    private val stackOverFlowApi: StackoverflowApi get() = appCompositionRoot.stackOverFlowApi

    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackOverFlowApi)
    val fetchQuestionDetailUseCase get() = FetchQuestionDetailUseCase(stackOverFlowApi)
}