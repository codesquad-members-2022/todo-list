//
//  Log.swift
//  todo-list
//
//  Created by Jason on 2022/04/07.
//

import Foundation
import UIKit

struct Log: Decodable {
    init(userId: String, action: Action, created: Date, taskId: Int, fromStatus: TaskStatus?, toStatus: TaskStatus?,
         taskTitle: String) {
        self.userId = userId
        self.action = action
        self.createdAt = created
        self.taskId = taskId
        self.fromStatus = fromStatus
        self.toStatus = toStatus
        self.taskTitle = taskTitle
    }
    
    let userId: String
    let action: Action
    let createdAt: Date
    let taskId: Int
    let fromStatus: TaskStatus?
    let toStatus: TaskStatus?
    let taskTitle: String
    
//    enum Codingkeys: String, Codable {
//        case from = "fromStatus"
//        case to = "toStatus"
//    }
    
}
