package com.techyourchance.dagger2course.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.questions.FetchQuestionDetailUseCase
import com.techyourchance.dagger2course.screens.common.ScreenNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import kotlinx.coroutines.*

class QuestionDetailsActivity : BaseActivity(), QuestionDetailViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var toolbar: MyToolbar
    private lateinit var questionId: String
    private lateinit var fetchQuestionDetailUseCase: FetchQuestionDetailUseCase
    private lateinit var dialogNavigator: DialogNavigator
    private lateinit var screenNavigator: ScreenNavigator

    private lateinit var viewMvc: QuestionDetailViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = QuestionDetailViewMvc(LayoutInflater.from(this), null)
        setContentView(viewMvc.rootView) // viewMvcのrootViewをセットしないと画面にコンテンツが描画されない

        screenNavigator = compositionRoot.screenManager

        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!

        fetchQuestionDetailUseCase = compositionRoot.fetchQuestionDetailUseCase

        dialogNavigator = compositionRoot.dialogNavigator
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                val result = fetchQuestionDetailUseCase.fetchQuestionDetails(questionId)
                when (result) {
                    is FetchQuestionDetailUseCase.Result.Success -> {
                        val questionBody = result.question.body
                        viewMvc.bindQuestionBody(questionBody)
                    }
                    is FetchQuestionDetailUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                viewMvc.hideProgressIndication()
            }

        }
    }

    private fun onFetchFailed() {
        dialogNavigator.showServerErrorDialog()
    }

    override fun onBackClicked() {
        screenNavigator.navigateBack()
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

}