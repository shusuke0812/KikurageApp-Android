package com.shusuke.kikurage.screen.sidemenu.wifi.selectdevice

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.shusuke.kikurage.utility.CustomTimber
import com.shusuke.kikurage.utility.bluetooth.BluetoothManager
import com.shusuke.kikurage.utility.bluetooth.BluetoothManagerDelegate
import com.shusuke.kikurage.utility.bluetooth.BluetoothManagerInterface
import com.shusuke.kikurage.utility.bluetooth.entity.DiscoveredDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WiFiSelectDeviceViewModel @Inject constructor(
    private val bluetoothManager: BluetoothManagerInterface
) : ViewModel(), BluetoothManagerDelegate {
    init {
        bluetoothManager.delegate = this
    }

    fun requestBluetoothFeature(activity: Activity) {
        if(!bluetoothManager.isEnabled()) {
            bluetoothManager.requestBluetoothFeature(activity)
            return
        }
    }

    fun registerBluetoothReceiver(activity: Activity) {
        bluetoothManager.registerReceiver(activity)
    }

    fun scanForPeripherals() {
        bluetoothManager.scanForPeripherals()
    }

    override fun didDiscoverDevice(manager: BluetoothManager, device: DiscoveredDevice) {
        CustomTimber.d("device=$device")
    }
}