package com.techyourchance.dagger2course.common.composition

import androidx.annotation.UiThread
import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionDetailUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@UiThread
class AppCompositionRoot {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val stackOverFlowApi: StackoverflowApi by lazy {
        retrofit.create(StackoverflowApi::class.java)
    }

//    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackOverFlowApi)
//    val fetchQuestionDetailUseCase get() = FetchQuestionDetailUseCase(stackOverFlowApi)

}