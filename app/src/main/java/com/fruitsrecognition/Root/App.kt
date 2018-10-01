package com.fruitsrecognition.Root

import android.app.Application
import com.fruitsrecognition.Model.NeuralNetwork.MockedRecogniser
import com.fruitsrecognition.Presenter.AppPresenterImpl
import com.fruitsrecognition.Root.Dagger.AppComponent
import com.fruitsrecognition.Root.Dagger.AppModule
import com.fruitsrecognition.Root.Dagger.DaggerAppComponent

/**
 * Created on 21.09.18.
 */
class App : Application() {
    private lateinit var appComponent : AppComponent

    public fun getAppComponent() : AppComponent{
        return appComponent
    }



    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(AppPresenterImpl(this),
                MockedRecogniser()
                )).build()

    }
}