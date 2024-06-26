package com.o5appstudio.composeconcepts

import android.app.Application
import androidx.hilt.navigation.compose.hiltViewModel
import com.o5appstudio.composeconcepts.testing.viewmodels.TestingViewModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()


    }
}