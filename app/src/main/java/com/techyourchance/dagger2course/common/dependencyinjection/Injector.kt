package com.techyourchance.dagger2course.common.dependencyinjection

import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListFragment

class Injector(private val compositionRoot: PresentationCompositionRoot) {

    fun inject(fragment: QuestionsListFragment) {
        fragment.dialogNavigator = compositionRoot.dialogNavigator
        fragment.fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
        fragment.screenNavigator = compositionRoot.screenManager
        fragment.viewMvcFactory = compositionRoot.viewMvcFactory
    }

    fun inject(activity: QuestionDetailsActivity) {
        activity.screenNavigator = compositionRoot.screenManager
        activity.dialogNavigator = compositionRoot.dialogNavigator
        activity.fetchQuestionDetailUseCase = compositionRoot.fetchQuestionDetailUseCase
        activity.viewMvcFactory = compositionRoot.viewMvcFactory
    }

}