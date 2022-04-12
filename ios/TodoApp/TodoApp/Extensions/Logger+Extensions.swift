//
//  Logger+Extensions.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/11.
//

import OSLog

extension Logger {
    private static let subsystem = Bundle.main.bundleIdentifier!
    static let network = Logger(subsystem: subsystem, category: "Network")
    static let viewCycle = Logger(subsystem: subsystem, category: "ViewCycle")
    static let view = Logger(subsystem: subsystem, category: "View")
}
