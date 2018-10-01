package com.fruitsrecognition.Root.Dagger

import com.fruitsrecognition.Model.NeuralNetwork.FruitRecogniser
import com.fruitsrecognition.Model.BackgroundTask.FruitsSource
import com.fruitsrecognition.Model.BackgroundTask.ImageResolver
import com.fruitsrecognition.Presenter.AppPresenter
import com.fruitsrecognition.Presenter.AppPresenterImpl
import com.fruitsrecognition.Presenter.FruitFoundCallback
import dagger.Module
import dagger.Provides

/**
 * Dagger module
 */
@Module
class AppModule(val presenter: AppPresenterImpl, val recogniser: FruitRecogniser) {

    @Provides
    fun getFruitFoundCallback(): FruitFoundCallback {
        return presenter
    }

    @Provides
    fun getFruitsSource(): FruitsSource {
        return presenter
    }

    @Provides
    fun getAppPresenter(): AppPresenter{
        return presenter
    }

    @Provides
    fun getFruitRecogniser(): FruitRecogniser {
        return recogniser
    }

    @Provides
    fun getImageResolver() : ImageResolver {
        return ImageResolver()
    }

}