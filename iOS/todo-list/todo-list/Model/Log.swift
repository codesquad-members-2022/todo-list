//
//  Log.swift
//  todo-list
//
//  Created by Jason on 2022/04/07.
//

import Foundation
import UIKit

struct Log: Decodable {
    init(userId: String, action: Action, created: Date, taskId: Int, from: TaskStatus?, to: TaskStatus?,
         taskTitle: String) {
        self.userId = userId
        self.action = action
        self.createdAt = created
        self.taskId = taskId
        self.from = from
        self.to = to
        self.taskTitle = taskTitle
    }
    
    let userId: String
    let action: Action
    let createdAt: Date
    let taskId: Int
    let from: TaskStatus?
    let to: TaskStatus?
    let taskTitle: String
    
    enum Codingkeys: String, CodingKey {
        case from = "fromStatus"
        case to = "toStatus"
    }
    
}
