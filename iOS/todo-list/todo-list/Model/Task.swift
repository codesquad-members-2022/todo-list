//
//  Task.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/06.
//

import Foundation

struct Task: Decodable {
    let id: Int
    var status: TaskStatus
    var title: String
    var content: String
    var authoredDevice: Device
}

enum TaskStatus: Decodable {
    case todo
    case inProgress
    case done
}

enum Device: Decodable {
    case iOS
    case android
    case web
}
