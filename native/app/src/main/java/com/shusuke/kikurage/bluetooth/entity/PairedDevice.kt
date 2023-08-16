package com.shusuke.kikurage.bluetooth.entity

data class PairedDevice(
    val name: String,
    val serviceUUID: String
)

data class PairedDeviceList(
    val items: MutableList<PairedDevice> = mutableListOf()
)
