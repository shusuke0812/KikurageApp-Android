package com.shusuke.kikurage.screen.sidemenu.wifi.selectdevice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shusuke.kikurage.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WiFiSelectDeviceFragment : Fragment() {
    private lateinit var viewModel: WiFiSelectDeviceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wifi_select_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[WiFiSelectDeviceViewModel::class.java]

        // Bluetooth
        viewModel.registerBluetoothReceiver(requireActivity())
        viewModel.scanForPeripherals()
    }
}