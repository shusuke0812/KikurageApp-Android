package com.shusuke.kikurage.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import com.shusuke.kikurage.bluetooth.entity.PairedDevice
import com.shusuke.kikurage.bluetooth.entity.PairedDeviceList

class KikurageBluetoothManager(
    private val _bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
) {
    //region Config
    fun isSupported(): Boolean {
        return _bluetoothAdapter == null
    }
    fun hasPermission(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
    }
    @RequiresPermission(value = "android.permission.BLUETOOTH_CONNECT")
    fun getPairedDevices(): PairedDeviceList {
        val pairedDevice = _bluetoothAdapter?.bondedDevices
        val pairedDeviceList = PairedDeviceList()

        pairedDevice?.forEach { device ->
            val deviceName = device.name
            val deviceMacAddress = device.address
            val pairedDevice = PairedDevice(name = deviceName, macAddress = deviceMacAddress)
            pairedDeviceList.items.add(pairedDevice)
        }
        return pairedDeviceList
    }
    //endregion
}