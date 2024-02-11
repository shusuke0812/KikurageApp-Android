package com.shusuke.kikurage.utility.bluetooth

import android.bluetooth.le.ScanFilter

sealed interface KikurageBluetoothUUID {
    val uuidString: String
    val scanFilter: ScanFilter
}

data class BleServiceUUID(
    override val scanFilter: ScanFilter,
    override val uuidString: String
) : KikurageBluetoothUUID