package com.shusuke.kikurage.utility.bluetooth

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.BluetoothLeScanner
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.shusuke.kikurage.utility.bluetooth.entity.DiscoveredDevice
import com.shusuke.kikurage.utility.bluetooth.entity.PairedDevice
import com.shusuke.kikurage.utility.bluetooth.entity.PairedDeviceList
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface KikurageBluetoothManagerDelegate {
    fun didDiscoverDevice(manager: KikurageBluetoothManager, device: DiscoveredDevice)
}

@SuppressLint("MissingPermission")
class KikurageBluetoothManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val bluetoothManager: BluetoothManager
    private val bluetoothAdapter: BluetoothAdapter
    private val bluetoothScanner: BluetoothLeScanner

    init {
        bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter
        bluetoothScanner = bluetoothAdapter.bluetoothLeScanner
    }

    var delegate: KikurageBluetoothManagerDelegate? = null

    //region Config
    fun isEnabled(): Boolean {
        return bluetoothAdapter.isEnabled
    }

    fun requestBluetoothFeature(activity: Activity) {
        val enabledIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        activity.startActivity(enabledIntent)
    }

    fun getPairedDevices(): PairedDeviceList {
        val pairedDevice = bluetoothAdapter.bondedDevices
        val pairedDeviceList = PairedDeviceList()

        pairedDevice?.forEach { device ->
            val deviceName = device.name
            val deviceMacAddress = device.address
            val pairedDevice = PairedDevice(name = deviceName, macAddress = deviceMacAddress)
            pairedDeviceList.items.add(pairedDevice)
        }
        return pairedDeviceList
    }

    fun registerReceiver(context: Context) {
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context.registerReceiver(receiver, filter)
    }
    //endregion

    //region Scan
    fun scanForPeripherals() {
        bluetoothAdapter.startDiscovery()
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