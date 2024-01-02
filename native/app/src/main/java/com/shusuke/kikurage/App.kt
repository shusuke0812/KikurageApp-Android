package com.shusuke.kikurage

import android.app.Application
import android.util.Log
import timber.log.Timber

class KikurageApp : Application() {
    override fun onCreate() {
        super.onCreate()

        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}