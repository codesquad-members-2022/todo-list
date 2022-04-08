//
//  Task.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/06.
//

import Foundation

struct Task: Decodable {
    let id: Int
    let rowPosition: Int
    let status: TaskStatus
    let title: String
    let content: String
    let device: Device
    let createdAt: Date
    let modifiedAt: Date
}

enum TaskStatus: String, Decodable {
    case todo = "TODO"
    case inProgress = "IN_PROGRESS"
    case done = "DONE"
}

enum Device: String, Decodable {
    case iOS = "IOS"
    case android = "ANDROID"
    case web = "WEB"
}
