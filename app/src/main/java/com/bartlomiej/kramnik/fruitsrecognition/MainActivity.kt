package com.bartlomiej.kramnik.fruitsrecognition

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Camera
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {

    val PERMISSION_CAMERA = 34
    private var mCamera: Camera? = null
    private var mMonitor: CameraMonitor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSION_CAMERA)
        }

        mCamera = getCameraInstance()

        mMonitor = mCamera?.let {
            CameraMonitor(this, it)
        }

        mMonitor?.let {
            val preview: FrameLayout = findViewById(R.id.camera_view)
            preview.addView(it)
        }

        mCamera?.let {
            it.setDisplayOrientation(90)
        }


    }

    private fun getCameraInstance() : Camera?{
        return try {
            Camera.open()
        }
        catch (e:Exception  ){
            return null
        }
    }

}
