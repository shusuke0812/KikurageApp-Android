package com.shusuke.kikurage.bluetooth

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import com.shusuke.kikurage.bluetooth.entity.DiscoveredDevice
import com.shusuke.kikurage.bluetooth.entity.PairedDevice
import com.shusuke.kikurage.bluetooth.entity.PairedDeviceList

interface KikurageBluetoothManagerDelegate {
    fun didDiscoverDevice(manager: KikurageBluetoothManager, device: DiscoveredDevice)
}
class KikurageBluetoothManager(
    private val _bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter(),
    val delegate: KikurageBluetoothManagerDelegate
) : KikurageBluetoothManagerDelegate by delegate {
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

    //region Scan
    @RequiresPermission(value = "android.permission.BLUETOOTH_SCAN")
    fun scanForPeripherals() {
        _bluetoothAdapter?.startDiscovery()
    }


    private val receiver = object : BroadcastReceiver() {
        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice? = intent?.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device?.name ?: ""
                    val deviceMacAddress = device?.address ?: ""
                    val discoveredDevice = DiscoveredDevice(name = deviceName, macAddress = deviceMacAddress)
                    delegate.didDiscoverDevice(this@KikurageBluetoothManager, discoveredDevice)
                }
            }
        }
    }
    //endregion
}