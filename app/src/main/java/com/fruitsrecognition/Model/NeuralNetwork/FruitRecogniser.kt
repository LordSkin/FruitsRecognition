package com.fruitsrecognition.Model.NeuralNetwork

/**
 * Created on 16.09.18.
 */

public interface FruitRecogniser {

    public fun recognise(data: Array<DoubleArray>) : Fruit

    public fun isFruit(data: Array<DoubleArray>) : Boolean

    public fun setUp()
    
}