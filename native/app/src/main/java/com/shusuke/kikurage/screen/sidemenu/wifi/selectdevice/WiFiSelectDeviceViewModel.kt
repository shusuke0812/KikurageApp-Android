package com.shusuke.kikurage.screen.sidemenu.wifi.selectdevice

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.shusuke.kikurage.utility.CustomTimber
import com.shusuke.kikurage.utility.bluetooth.KBluetoothManager
import com.shusuke.kikurage.utility.bluetooth.KBluetoothManagerDelegate
import com.shusuke.kikurage.utility.bluetooth.entity.DiscoveredDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WiFiSelectDeviceViewModel @Inject constructor(
    private val bluetoothManager: KBluetoothManager
) : ViewModel(), KBluetoothManagerDelegate {
    init {
        bluetoothManager.delegate = this
    }

    fun requestBluetoothFeature(activity: Activity) {
        if(!bluetoothManager.isEnabled()) {
            bluetoothManager.requestBluetoothFeature(activity)
            return
        }
    }

    fun scanForPeripherals() {
        bluetoothManager.scanForPeripherals()
    }

    override fun didDiscoverDevice(manager: KBluetoothManager, device: DiscoveredDevice) {
        CustomTimber.d("device=${device.name}, ${device.address}")
    }
}