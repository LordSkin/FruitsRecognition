package com.fruitsrecognition.Presenter

import com.fruitsrecognition.Model.Entities.Fruit

/**
 * Created on 17.09.18.
 */

public interface FruitFoundCallback{
    fun fruitFound(fruit: Fruit)

    fun fruitNotFound()
}