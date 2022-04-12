//
//  TaskStorage.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/12.
//

import Foundation
import OSLog

class TaskStorage {
    private var dict: [Int: Task] = [:]
    
    func all() -> [Task] {
        return Array(dict.values)
    }
    
    func overwrite(tasks: [Task]) {
        dict = [:]
        tasks.forEach { add(task: $0) }
    }
    
    func add(task: Task) {
        if dict[task.id] != nil {
            return SystemLog.fault("\(task.id) already exists")
        }
        dict[task.id] = task
    }
    
    func update(task: Task) {
        if dict[task.id] == nil {
            return SystemLog.fault("\(task.id) does not exists")
        }
        dict[task.id] = task
    }
    
    func delete(id: Int) {
        dict[id] = nil
    }
}
