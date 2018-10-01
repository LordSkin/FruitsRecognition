package com.fruitsrecognition.Model.BackgroundTask

import android.hardware.Camera
import android.os.AsyncTask
import android.util.Log
import com.fruitsrecognition.Model.NeuralNetwork.FruitRecogniser
import com.fruitsrecognition.Presenter.FruitFoundCallback
import javax.inject.Inject


/**
 * Created on 16.09.18.
 */

public class RecognitionTask
    : AsyncTask<Void, Void, Void>(), Camera.PreviewCallback {


    private var isProcessing = false

    @Inject
    lateinit var fruitFoundCallback: FruitFoundCallback

    @Inject
    lateinit var fruitsSource: FruitsSource

    @Inject
    lateinit var recogniser: FruitRecogniser

    @Inject
    lateinit var resolver : ImageResolver

    override fun onPreviewFrame(p0: ByteArray?, p1: Camera?) {
        try {
            if (!isProcessing) {
                isProcessing = true
                val resolvedImage = resolver.resolveImage(p0!!, p1!!)
                if (recogniser.isFruit(resolvedImage)) {
                    val f = recogniser.recognise(resolvedImage)
                    Log.d("Fruit found: ", f.name)
                    fruitFoundCallback.fruitFound(f)
                }
            }
        } finally {
            isProcessing = false
        }
    }

    override fun doInBackground(vararg p0: Void?): Void? {
        Thread.sleep(3000)
        fruitsSource.getImage(this)
        return null
    }



}
