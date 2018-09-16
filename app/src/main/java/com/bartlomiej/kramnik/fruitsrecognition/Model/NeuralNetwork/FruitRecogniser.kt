package com.bartlomiej.kramnik.fruitsrecognition.Model.NeuralNetwork

/**
 * Created on 16.09.18.
 */

public interface FruitRecogniser {

    public fun recognise(data: Array<IntArray>) : Fruit

    public fun isFruit(data: Array<IntArray>) : Boolean

    public fun setUp()
    
}