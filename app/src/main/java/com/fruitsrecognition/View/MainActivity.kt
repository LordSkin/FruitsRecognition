package com.fruitsrecognition.View

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Camera
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.FrameLayout
import android.widget.TextView
import com.fruitsrecognition.Model.Entities.Fruit
import com.fruitsrecognition.Presenter.AppPresenter
import com.fruitsrecognition.R
import com.fruitsrecognition.Root.App
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var fruitTextView: TextView
    private var mCamera: Camera? = null
    private lateinit var mMonitor: CameraMonitor
    val PERMISSION_CAMERA = 34

    @Inject
    lateinit var presenter: AppPresenter

    override fun fruitNotFound() {
        runOnUiThread(Runnable { fruitTextView?.text = getString(R.string.fruit_not_found) })
    }

    override fun showFruit(fruit: Fruit) {
        runOnUiThread(Runnable { fruitTextView?.text = fruit.name })
    }

    override fun getFrame(callback: Camera.PreviewCallback) {
        mCamera?.startPreview()
        mCamera?.setPreviewCallback(callback)
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

        (application as App).getAppComponent().inject(this)

        presenter.startView(this)

    }

    override fun onPause() {
        super.onPause()
        presenter.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stopCamera()
    }

    private fun getCameraInstance(): Camera? {
        return try {
            Camera.open()
        } catch (e: Exception) {
            return null
        }
    }

}
