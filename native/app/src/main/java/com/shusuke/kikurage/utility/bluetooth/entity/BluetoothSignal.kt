package com.shusuke.kikurage.utility.bluetooth.entity

import android.content.Context
import android.graphics.drawable.Drawable
import com.shusuke.kikurage.R

enum class BluetoothSignal {
    LOST,
    WEAK,
    FAIR,
    GOOD;

    companion object {
        // NOTE: Based on experiment value using LightBlue App.
        fun getSignal(rssi: Int): BluetoothSignal {
            val absRssi = kotlin.math.abs(rssi)
            return when {
                absRssi < 45 -> GOOD
                absRssi < 60 -> FAIR
                absRssi < 80 -> WEAK
                else -> LOST
            }
        }
    }

    fun getImage(context: Context): Drawable? {
        return when(this) {
            LOST -> R.drawable.signal_lost
            WEAK -> R.drawable.signal_weak
            FAIR -> R.drawable.signal_fair
            GOOD -> R.drawable.signal_good
        }.let { resourceId ->
            context.getDrawable(resourceId)
        }
    }
}