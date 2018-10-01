package com.fruitsrecognition.Presenter

import android.hardware.Camera
import com.fruitsrecognition.Model.Entities.Fruit
import com.fruitsrecognition.Model.BackgroundTask.FruitsSource
import com.fruitsrecognition.Model.BackgroundTask.RecognitionTask
import com.fruitsrecognition.Root.App
import com.fruitsrecognition.View.MainView

/**
 * App presenter implementation
 */

class AppPresenterImpl(val app : App) : FruitFoundCallback, AppPresenter, FruitsSource {

    private lateinit var view : MainView
    private  var recognitionTask = RecognitionTask()

    override fun getImage(callback: Camera.PreviewCallback) {
        view.getFrame(callback)
    }

    override fun stopCamera() {
        recognitionTask.cancel(true)
    }



    override fun startView(view: MainView) {
        this.view = view
        app.getAppComponent().inject(recognitionTask)
        recognitionTask.execute(null)
    }

    override fun fruitFound(fruit: Fruit) {
        view.showFruit(fruit)
    }

    override fun fruitNotFound() {
        view.fruitNotFound()
    }

}