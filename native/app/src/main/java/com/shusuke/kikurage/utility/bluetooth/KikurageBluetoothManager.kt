package com.shusuke.kikurage.utility.bluetooth

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.shusuke.kikurage.utility.bluetooth.entity.DiscoveredDevice
import com.shusuke.kikurage.utility.bluetooth.entity.PairedDevice
import com.shusuke.kikurage.utility.bluetooth.entity.PairedDeviceList

interface KikurageBluetoothManagerDelegate {
    fun didFinishCheckPermissions(manage: KikurageBluetoothManager)
    fun didDiscoverDevice(manager: KikurageBluetoothManager, device: DiscoveredDevice)
}

@SuppressLint("MissingPermission")
class KikurageBluetoothManager(
    private val _bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter(),
    var delegate: KikurageBluetoothManagerDelegate? = null
) {
    //region Config
    private fun isSupported(): Boolean {
        return _bluetoothAdapter != null && !_bluetoothAdapter.isEnabled
    }

    /**
     * Usage: In Fragment,
     * val manager = KikurageBluetoothManager()
     * manager.checkPermission(this.requireActivity())
     */
    fun checkPermission(activity: Activity) {
        if (isSupported()) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            if (ActivityCompat.checkSelfPermission(
                    activity,
                    android.Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                activity.requestPermissions(arrayOf(android.Manifest.permission.BLUETOOTH_CONNECT), 2)
                delegate?.didFinishCheckPermissions(this)
                return
            }
            activity.startActivityForResult(enableBtIntent, 1)
            delegate?.didFinishCheckPermissions(this)
            return
        }
        delegate?.didFinishCheckPermissions(this)
    }
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
    fun scanForPeripherals() {
        _bluetoothAdapter?.startDiscovery()
    }


    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice? = intent?.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device?.name ?: ""
                    val deviceMacAddress = device?.address ?: ""
                    val discoveredDevice = DiscoveredDevice(name = deviceName, macAddress = deviceMacAddress)
                    delegate?.didDiscoverDevice(this@KikurageBluetoothManager, discoveredDevice)
                }
            }
        }
    }
    //endregion
}