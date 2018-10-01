package com.fruitsrecognition.Root.Dagger

import com.fruitsrecognition.Model.BackgroundTask.RecognitionTask
import com.fruitsrecognition.View.MainActivity
import dagger.Component

/**
 * Dagger component for app
 */
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(task: RecognitionTask)
    fun inject(view: MainActivity)
}