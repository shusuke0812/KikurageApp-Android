package com.shusuke.kikurage

import android.app.Application
import com.shusuke.kikurage.utility.CustomTimber
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KikurageApp : Application() {
    override fun onCreate() {
        super.onCreate()

        CustomTimber.setup()
    }
}