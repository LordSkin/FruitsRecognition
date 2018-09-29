package com.fruitsrecognition.Model.NeuralNetwork

import java.util.*

/**
 * Created on 30.09.18.
 */
class MockedRecogniser : FruitRecogniser {

    val random = Random()

    override fun recognise(data: Array<DoubleArray>): Fruit {
        if(random.nextBoolean()){
            return Fruit.APPLE
        }
        else{
            return Fruit.BANANA
        }
    }

    override fun isFruit(data: Array<DoubleArray>): Boolean {
        return random.nextBoolean()
    }

    override fun setUp() {

    }
}