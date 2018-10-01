package com.fruitsrecognition.Model.BackgroundTask

import android.graphics.*
import android.hardware.Camera
import java.io.ByteArrayOutputStream

/**
 * Created on 02.10.2018.
 */
class ImageResolver {

    public fun resolveImage(bytes: ByteArray, camera: Camera): Array<DoubleArray> {

        val width = camera.parameters.getPreviewSize().width
        val height = camera.parameters.getPreviewSize().height

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