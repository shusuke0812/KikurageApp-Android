package com.shusuke.kikurage.utility.bluetooth

import android.os.ParcelUuid
import java.util.UUID

const val localDeviceName = "kikurage-device-m5-stack"

object KBluetoothUUID {
    object Service {
        val m5Stack = UUID.fromString("65609901-b6ed-45cc-b8af-b4055a9b7666")
    }
    object Characteristic {
        val writeStopWiFiScan = UUID.fromString("65609902-b6ed-45cc-b8af-b4055a9b7666")
        val writeWiFiSetting = UUID.fromString("65609904-b6ed-45cc-b8af-b4055a9b7666")
        val readWiFi = UUID.fromString("65609903-b6ed-45cc-b8af-b4055a9b7666")
        val readCompletion = UUID.fromString("65609905-b6ed-45cc-b8af-b4055a9b7666")
    }
}

val UUID.parcelUuid: ParcelUuid
    get() = ParcelUuid(this)