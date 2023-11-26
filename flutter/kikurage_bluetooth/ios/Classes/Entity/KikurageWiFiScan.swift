//
//  KikurageWiFiScan.swift
//  kikurage_bluetooth
//
//  Created by Shusuke Ota on 2023/11/26.
//

import Foundation

struct KikurageWiFiScan: Encodable {
    private let isStop: Bool

    init(isStop: Bool) {
        self.isStop = isStop
    }

    enum CodingKeys: String, CodingKey {
        case isStop = "is_stop"
    }
}
