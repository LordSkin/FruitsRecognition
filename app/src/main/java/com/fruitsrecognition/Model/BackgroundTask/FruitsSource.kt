package com.fruitsrecognition.Model.BackgroundTask

import android.hardware.Camera

/**
 * Created on 16.09.18.
 */
public interface FruitsSource{
    public fun getImage(callback: Camera.PreviewCallback)
}