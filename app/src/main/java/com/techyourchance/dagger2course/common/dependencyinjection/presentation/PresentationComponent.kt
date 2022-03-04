package com.techyourchance.dagger2course.common.dependencyinjection.presentation

import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityComponent
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityModule
import com.techyourchance.dagger2course.questions.FetchQuestionDetailUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreenNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListFragment
import dagger.Component

@PresentationScope
@Component(dependencies = [ActivityComponent::class], modules = [PresentationModule::class])
interface PresentationComponent {

    fun inject(fragment: QuestionsListFragment)
    fun inject(activity: QuestionDetailsActivity)

}