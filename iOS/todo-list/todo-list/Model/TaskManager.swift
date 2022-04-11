//
//  TaskManager.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/11.
//

import Foundation
import OSLog

struct TaskManager {
    private let tasks: TaskStorage = TaskStorage()
    private let networkManager = NetworkManager()
    
    func load() {
        networkManager.getAllTasks { result in
            switch result {
            case .success(let data): tasks.overwrite(tasks: data)
            case .failure(let error): print(error.localizedDescription)
            }
        }
    }
    
    func add(state: TaskStatus, title: String, content: String) {
        let newTask = Task(status: state, title: title, content: content)
        
        networkManager.post(item: newTask) { result in
            switch result {
            case .success(let data): tasks.add(task: data)
            case .failure(let error): print(error.localizedDescription)
            }
        }
    }
    
    func delete(id: Int) {
        networkManager.delete(id: id) { result in
            switch result {
            case .success(let id): tasks.delete(id: id)
            case .failure(let error): print(error.localizedDescription)
            }
        }
    }
    
    func update(id: Int, state: TaskStatus, title: String, content: String) {
        let newTask = Task(status: state, title: title, content: content, id: id)
        
        networkManager.patch(item: newTask) { result in
            switch result {
            case .success(let data): tasks.update(task: data)
            case .failure(let error): print(error.localizedDescription)
            }
        }
    }
    
    func save() {
        networkManager.patch(item: tasks.all()) { result in
            switch result {
            case .success(let data): tasks.overwrite(tasks: data)
            case .failure(let error): print(error.localizedDescription)
            }
        }
    }
}



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
            return Logger.init().fault("\(task.id) already exists")
        }
        dict[task.id] = task
    }
    
    func update(task: Task) {
        if dict[task.id] == nil {
            return Logger.init().fault("\(task.id) does not exists")
        }
        dict[task.id] = task
    }
    
    func delete(id: Int) {
        dict[id] = nil
    }
}
