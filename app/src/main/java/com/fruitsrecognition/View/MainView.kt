package com.fruitsrecognition.View

import android.hardware.Camera
import com.fruitsrecognition.Model.NeuralNetwork.Fruit

/**
 * Created on 17.09.18.
 */
interface MainView {
    fun showFruit(fruit : Fruit)
    fun fruitNotFound()
    fun getFrame(callback: Camera.PreviewCallback)
}