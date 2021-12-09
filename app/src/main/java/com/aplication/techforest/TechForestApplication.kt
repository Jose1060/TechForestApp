package com.aplication.techforest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TechForestApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}