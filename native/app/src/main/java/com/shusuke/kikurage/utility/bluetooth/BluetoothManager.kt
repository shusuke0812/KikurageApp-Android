package com.shusuke.kikurage.utility.bluetooth

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.shusuke.kikurage.utility.bluetooth.entity.DiscoveredDevice
import com.shusuke.kikurage.utility.bluetooth.entity.PairedDevice
import com.shusuke.kikurage.utility.bluetooth.entity.PairedDeviceList
import javax.inject.Inject

interface BluetoothManagerInterface {
    fun isEnabled(): Boolean
    fun requestBluetoothFeature(activity: Activity)
    fun registerReceiver(context: Context)
    fun getPairedDevices(): PairedDeviceList
    fun scanForPeripherals()
    var delegate: BluetoothManagerDelegate?
}

interface BluetoothManagerDelegate {
    fun didDiscoverDevice(manager: BluetoothManager, device: DiscoveredDevice)
}

@SuppressLint("MissingPermission")
class BluetoothManager @Inject constructor() : BluetoothManagerInterface {
    private val _bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    override var delegate: BluetoothManagerDelegate? = null

    //region Config
    override fun isEnabled(): Boolean {
        return _bluetoothAdapter != null && _bluetoothAdapter.isEnabled
    }

    override fun requestBluetoothFeature(activity: Activity) {
        val enabledIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        activity.startActivity(enabledIntent)
    }

    override fun getPairedDevices(): PairedDeviceList {
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

    override fun registerReceiver(context: Context) {
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context.registerReceiver(receiver, filter)
    }
    //endregion

    //region Scan
    override fun scanForPeripherals() {
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
                    delegate?.didDiscoverDevice(this@BluetoothManager, discoveredDevice)
                }
            }
        }
    }
    //endregion
}