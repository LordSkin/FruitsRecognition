package com.bartlomiej.kramnik.fruitsrecognition.Model.NeuralNetwork

import android.os.AsyncTask
import com.bartlomiej.kramnik.fruitsrecognition.Presenter.FruitFoundCallback

/**
 * Created on 16.09.18.
 */

public class RecognitionTask(private val fruitFoundCallback : FruitFoundCallback,
                             private val fruitsSource: FruitsSource, private val recogniser : FruitRecogniser)
    : AsyncTask<Void, Void, Void>() {



    override fun doInBackground(vararg p0: Void?): Void {
        while (true){
            fruitsSource.let {
                var image = it.getImage()

                if (recogniser.isFruit(image)){
                    fruitFoundCallback.fruitFound(recogniser.recognise(image))
                }
            }
        }
    }
}
