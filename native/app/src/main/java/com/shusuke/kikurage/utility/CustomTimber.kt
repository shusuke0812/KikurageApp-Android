package com.shusuke.kikurage.utility

import com.shusuke.kikurage.BuildConfig
import timber.log.Timber

class CustomTimber {
    companion object {
        fun d(message: String) {
            Timber.tag("🔥").d(message)
        }

        fun setup() {
            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
        }
    }
}