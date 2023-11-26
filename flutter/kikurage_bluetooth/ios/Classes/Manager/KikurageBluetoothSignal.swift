//
//  KikurageBluetoothSignal.swift
//  kikurage_bluetooth
//
//  Created by Shusuke Ota on 2023/11/26.
//

import UIKit

public enum KikurageBluetoothSignal {
    case lost
    case weak
    case fair
    case good

    public init() {
        self = .lost
    }

    // NOTE: Based on experiment value using LightBlue App.
    public static func getSignal(rssi: Int) -> Self {
        let _rssi = abs(rssi)
        if _rssi < 45 {
            return .good
        } else if _rssi < 60 {
            return .fair
        } else if _rssi < 80 {
            return .weak
        } else {
            return .lost
        }
    }
}
