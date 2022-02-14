package com.techyourchance.dagger2course.screens.common.viewmvc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

open class BaseViewMvc<LISTENER_TYPE>(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?,
    @LayoutRes private val layoutId: Int
) {
    val rootView: View = layoutInflater.inflate(layoutId, parent, false)

    protected val listeners = HashSet<LISTENER_TYPE>()

    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }

    fun <T : View?> findViewById(@IdRes id: Int): T {
        return rootView.findViewById<T>(id)
    }
}