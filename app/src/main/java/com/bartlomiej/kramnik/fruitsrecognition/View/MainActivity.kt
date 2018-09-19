package com.bartlomiej.kramnik.fruitsrecognition.View

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Camera
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.FrameLayout
import android.widget.TextView
import com.bartlomiej.kramnik.fruitsrecognition.Model.NeuralNetwork.Fruit
import com.bartlomiej.kramnik.fruitsrecognition.R

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var fruitTextView : TextView
    private var mCamera : Camera? = null
    private lateinit var mMonitor : CameraMonitor
    val PERMISSION_CAMERA = 34

    override fun fruitNotFound() {
        fruitTextView?.text = getString(R.string.fruit_not_found)
    }

    override fun showFruit(fruit: Fruit) {
        fruitTextView?.text = fruit.name
    }

    override fun getFrame(callback: Camera.PictureCallback) {
        mCamera?.takePicture(null, callback,null,null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fruitTextView = findViewById(R.id.fruit_text_view)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSION_CAMERA)
        }

        mCamera = getCameraInstance()

        mMonitor = mCamera?.let {
            CameraMonitor(this, it)
        }!!

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
