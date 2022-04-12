//
//  Log.swift
//  todo-list
//
//  Created by Jason on 2022/04/07.
//

import Foundation
import UIKit

struct Log: Decodable {
    init(userId: String, action: Action, created: Date, taskId: Int, from: TaskStatus?, to: TaskStatus?) {
        self.userId = userId
        self.activityType = action
        self.createdAt = created
        self.taskId = taskId
        self.from = from
        self.to = to
    }
    
    let userId: String
    private let activityType: Action
    private let createdAt: Date
    private let taskId: Int
    private let from: TaskStatus?
    private let to: TaskStatus?
    
    enum Codingkeys: String, CodingKey {
        case from = "fromStatus"
        case to = "toStatus"
    }
    
}
