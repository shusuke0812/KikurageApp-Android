package com.shusuke.kikurage.screen.home

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.shusuke.kikurage.utility.bluetooth.BluetoothPermissionManagerInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bluetoothPermissionManager: BluetoothPermissionManagerInterface
) : ViewModel() {
    fun requestBluetoothPermission(activity: Activity) {
        if (!bluetoothPermissionManager.hasConnectPermission(activity)) {
            bluetoothPermissionManager.requestPermissions(activity)
        }
    }
}