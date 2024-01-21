package com.shusuke.kikurage.utility.bluetooth

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import javax.inject.Inject

interface BluetoothPermissionManagerInterface {
    fun hasConnectPermission(context: Context): Boolean
    fun requestPermissions(activity: Activity)
}

class BluetoothPermissionManager @Inject constructor() : BluetoothPermissionManagerInterface {
    private enum class RequestCode(val value: Int) {
        PERMISSION(2)
    }

    override fun hasConnectPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * Usage: In Fragment,
     * val manager = BluetoothPermissionManager()
     * manager.checkPermission(this.requireActivity())
     *
     * Note:
     * https://developer.android.com/develop/connectivity/bluetooth/bt-permissions?hl=ja#declare-android12-or-higher
     */
    override fun requestPermissions(activity: Activity) {
        val permissions = arrayOf(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            permissions.plus(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            permissions.plus(Manifest.permission.BLUETOOTH_CONNECT)
            permissions.plus(Manifest.permission.BLUETOOTH_SCAN)
        }

        activity.requestPermissions(permissions, RequestCode.PERMISSION.value)
    }
}