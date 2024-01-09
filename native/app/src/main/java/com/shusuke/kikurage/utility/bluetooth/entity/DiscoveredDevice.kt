package com.shusuke.kikurage.utility.bluetooth.entity

data class DiscoveredDevice(
    val name: String,
    val serviceUUID: String
)

data class DiscoveredDeviceList(
    val items: MutableList<DiscoveredDevice> = mutableListOf()
)
