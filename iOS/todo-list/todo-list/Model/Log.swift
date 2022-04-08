//
//  Log.swift
//  todo-list
//
//  Created by Jason on 2022/04/07.
//

import Foundation
import UIKit

struct Log {
    internal init(userId: String, action: Action, created: Date, task: Task, from: TaskStatus?, to: TaskStatus?) {
        self.userId = userId
        self.action = action
        self.created = created
        self.task = task
        self.from = from
        self.to = to
    }
    
    
    let userId: String
    private let action: Action
    private let created: Date
    private let task: Task
    private let from: TaskStatus?
    private let to: TaskStatus?
    
}
