package com.shusuke.kikurage.utility.bluetooth.entity

import android.Manifest
import android.bluetooth.BluetoothDevice
import androidx.annotation.RequiresPermission

sealed interface BleDevice {
    val name: String?
    val address: String
}

data class DiscoveredDevice(
    val device: BluetoothDevice
) : BleDevice {
    override val name: String?
        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
        get() = device?.name

    override val address: String
        get() = device.address
}

data class DiscoveredDeviceList(
    val items: MutableList<DiscoveredDevice> = mutableListOf()
)
