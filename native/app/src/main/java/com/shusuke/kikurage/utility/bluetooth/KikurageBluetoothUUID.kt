package com.shusuke.kikurage.utility.bluetooth

import android.bluetooth.le.ScanFilter

object KikurageBluetoothUUID {
    object LocalName {
        val m5Stack = "kikurage-device-m5-stack"
    }
    object Service {
        val m5Stack = "65609901-b6ed-45cc-b8af-b4055a9b7666"
    }
    object Characteristic {
        val writeStopWiFiScan = "65609902-b6ed-45cc-b8af-b4055a9b7666"
        val writeWiFiSetting = "65609904-b6ed-45cc-b8af-b4055a9b7666"
        val readWiFi = "65609903-b6ed-45cc-b8af-b4055a9b7666"
        val readCompletion = "65609905-b6ed-45cc-b8af-b4055a9b7666"
    }
}