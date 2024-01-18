package com.shusuke.kikurage.screen.sidemenu.wifi.selectdevice

import androidx.lifecycle.ViewModel
import com.shusuke.kikurage.utility.bluetooth.KikurageBluetoothManagerInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WiFiSelectDeviceViewModel @Inject constructor(
    private val bluetoothManager: KikurageBluetoothManagerInterface
) : ViewModel() {

}