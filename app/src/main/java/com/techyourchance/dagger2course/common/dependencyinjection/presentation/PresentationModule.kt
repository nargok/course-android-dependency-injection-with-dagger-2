package com.techyourchance.dagger2course.common.dependencyinjection.presentation

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityComponent
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityScope
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionDetailUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreenNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule() {

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun dialogNavigator(fragmentManager: FragmentManager) = DialogNavigator(fragmentManager)

    @Provides
    fun fetchQuestionsUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionsUseCase(stackoverflowApi)

    @Provides
    fun fetchQuestionDetailUseCase(stackoverflowApi: StackoverflowApi) = FetchQuestionDetailUseCase(stackoverflowApi)

}