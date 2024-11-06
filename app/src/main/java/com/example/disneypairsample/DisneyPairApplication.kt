package com.example.disneypairsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DisneyPairApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}