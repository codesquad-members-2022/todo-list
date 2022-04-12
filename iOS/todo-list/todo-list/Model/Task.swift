//
//  Task.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/06.
//

import Foundation

struct Task: Codable {
    // User Generated
    let status: TaskStatus
    let title: String
    let content: String
    
    // System Generated
    var id: Int = 0
    var userId: String = "Eddy_User"
    var rowPosition: Int = 0
    var device: Device = .iOS
    var createdAt: Date = .now
    var modifiedAt: Date = .now
}

enum TaskStatus: String, Codable {
    case todo = "TODO"
    case inProgress = "IN_PROGRESS"
    case done = "DONE"
    case none = "NONE"
}

enum Device: String, Codable {
    case iOS = "IOS"
    case android = "ANDROID"
    case web = "WEB"
    case none = "NONE"
}
