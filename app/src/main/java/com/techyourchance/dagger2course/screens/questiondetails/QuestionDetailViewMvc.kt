package com.techyourchance.dagger2course.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import com.techyourchance.dagger2course.screens.common.viewmvc.BaseViewMvc

class QuestionDetailViewMvc(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?
) : BaseViewMvc<QuestionDetailViewMvc.Listener>(
    layoutInflater,
    null,
    R.layout.layout_question_details
) {

    interface Listener {
        fun onBackClicked()
    }

    private val toolbar: MyToolbar = findViewById(R.id.toolbar)
    private val txtQuestionBody: TextView = findViewById(R.id.txt_question_body)

    // init pull-down-to-refresh (used as a progress indicator)
    private val swipeRefresh: SwipeRefreshLayout = findViewById(R.id.swipeRefresh)

    init {
        swipeRefresh.isEnabled = false
        toolbar.setNavigateUpListener {
            for (listener in listeners) {
                listener.onBackClicked()
            }
        }
    }

    fun bindQuestionBody(questionBody: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtQuestionBody.text =
                Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            txtQuestionBody.text = Html.fromHtml(questionBody)
        }
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }

}