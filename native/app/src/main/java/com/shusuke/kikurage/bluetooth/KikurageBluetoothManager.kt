package com.shusuke.kikurage.bluetooth

import android.bluetooth.BluetoothAdapter

class KikurageBluetoothManager(
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
) {
    fun isSupported(): Boolean {
        return bluetoothAdapter == null
    }
}