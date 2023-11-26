//
//  KikurageBluetoothCentralState.swift
//  kikurage_bluetooth
//
//  Created by Shusuke Ota on 2023/11/26.
//

import CoreBluetooth
import Foundation

public struct KikurageBluetoothCentralState {
    public let value: CBManagerState

    public init(value: CBManagerState) {
        self.value = value
    }
}

public enum KikurageBluetoothConnectionState {
    case standby
    case connect
    case fail(Error?)
    case disconnect(Error?)
}

public enum KikurageBluetoothPeripheralState {
    case standby
    case didDiscoverCharacteristic
}
