package com.fruitsrecognition.Model.NeuralNetwork

import android.graphics.*
import android.hardware.Camera
import android.os.AsyncTask
import android.util.Log
import com.fruitsrecognition.Presenter.FruitFoundCallback
import java.io.ByteArrayOutputStream
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

    override fun onPreviewFrame(p0: ByteArray?, p1: Camera?) {
        try {
            if (!isProcessing) {
                isProcessing = true
                val resolvedImage = resolveImage(p0!!, p1!!)
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


    private fun resolveImage(bytes: ByteArray, camera: Camera): Array<DoubleArray> {

        val width = camera.getParameters().getPreviewSize().width
        val height = camera.getParameters().getPreviewSize().height

        val yuvImage = YuvImage(bytes, ImageFormat.NV21, width, height, null)

        val out = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(0, 0, width, height), 50, out)
        val imageBytes = out.toByteArray()
        val bitMap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        val scaledBitMap = Bitmap.createScaledBitmap(bitMap, 28, 28, false)
        var result = Array<DoubleArray>(28, { DoubleArray(28) })
        for (i in 0..27) {
            for (j in 0..27) {
                val color = bitMap.getPixel(i, j)
                val r = Color.red(color)
                val g = Color.green(color)
                val b = Color.blue(color)
                val luminance = 0.299 * r + 0.0 + 0.587 * g + 0.0 + 0.114 * b + 0.0
                result.get(i).set(j, luminance)
            }
        }

        return result
    }
}
