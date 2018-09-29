package com.fruitsrecognition.Presenter

import com.fruitsrecognition.View.MainView

/**
 * Created on 17.09.18.
 */
interface AppPresenter {

    fun startView(view : MainView)

    fun stopCamera()
}