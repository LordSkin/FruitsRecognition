package com.bartlomiej.kramnik.fruitsrecognition.View

import android.hardware.Camera
import android.widget.FrameLayout
import com.bartlomiej.kramnik.fruitsrecognition.Model.NeuralNetwork.Fruit

/**
 * Created on 17.09.18.
 */
interface MainView {
    fun showFruit(fruit : Fruit)
    fun fruitNotFound()
    fun getFrame(callback: Camera.PictureCallback)
}