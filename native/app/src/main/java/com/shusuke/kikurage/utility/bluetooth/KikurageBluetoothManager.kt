package com.shusuke.kikurage.utility.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.shusuke.kikurage.utility.bluetooth.entity.DiscoveredDevice
import com.shusuke.kikurage.utility.bluetooth.entity.PairedDevice
import com.shusuke.kikurage.utility.bluetooth.entity.PairedDeviceList
import javax.inject.Inject

interface KikurageBluetoothManagerInterface {
    fun isSupported(): Boolean
    fun getPairedDevices(): PairedDeviceList
    fun scanForPeripherals()
    var delegate: KikurageBluetoothManagerDelegate?
}

interface KikurageBluetoothManagerDelegate {
    fun didFinishCheckPermissions(manage: KikurageBluetoothManager)
    fun didDiscoverDevice(manager: KikurageBluetoothManager, device: DiscoveredDevice)
}

@SuppressLint("MissingPermission")
class KikurageBluetoothManager @Inject constructor() : KikurageBluetoothManagerInterface {
    private val _bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    override var delegate: KikurageBluetoothManagerDelegate? = null

    //region Config
    override fun isSupported(): Boolean {
        return _bluetoothAdapter != null && !_bluetoothAdapter.isEnabled
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
                    delegate?.didDiscoverDevice(this@KikurageBluetoothManager, discoveredDevice)
                }
            }
        }
    }
    //endregion
}