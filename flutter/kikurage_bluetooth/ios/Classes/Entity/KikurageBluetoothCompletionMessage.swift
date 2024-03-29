//
//  KikurageBluetoothCompletionMessage.swift
//  kikurage_bluetooth
//
//  Created by Shusuke Ota on 2023/11/26.
//

import Foundation

public struct KikurageBluetoothCompletionMessage: Decodable {
    let type: String
    let description: String

    enum CodingKeys: String, CodingKey {
        case type
        case description
    }

    public func getKikurageBluetoothCompletion() -> KikurageBluetoothCompletion {
        if type == "success" {
            switch description.lowercased() {
            case "wifi setting success":
                return .wifiSettingSuccess
            default:
                return .notFound
            }
        } else {
            switch description.lowercased() {
            case "wifi setting fail":
                return .wifiSettingFail
            default:
                return .notFound
            }
        }
    }
}

public enum KikurageBluetoothCompletion {
    case wifiSettingSuccess
    case wifiSettingFail
    case notFound
}
