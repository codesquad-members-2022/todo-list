//
//  SystemLog.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/12.
//

import Foundation
import OSLog

enum SystemLog {
    static func fault(_ message: String) {
        Logger().fault("\(message, privacy: .public)")
    }
    
    static func info(_ message: String) {
        Logger().info("\(message, privacy: .public)")
    }
}
