package com.shusuke.kikurage.utility.bluetooth

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.os.Handler
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

    private val handler = Handler()
    private val SCAN_PERIOD: Long = 10000 // ms

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
    //endregion

    //region Scan
    fun scanForPeripherals() {
        bluetoothScanner.startScan(leScanCallback)
        handler.postDelayed({ // To prevent drains the battery
            stopScan()
        }, SCAN_PERIOD)
    }

    fun stopScan() {
        bluetoothScanner.stopScan(leScanCallback)
    }

    private val leScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            result?.device?.let {
                delegate?.didDiscoverDevice(this@KikurageBluetoothManager, DiscoveredDevice(it))
            }
        }
    }
    //endregion
}