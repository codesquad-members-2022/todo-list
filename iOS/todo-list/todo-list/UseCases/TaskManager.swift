//
//  TaskManager.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/11.
//

import Foundation
import OSLog

struct TaskManager {
    static let shared = TaskManager()
    
    private let tasks: TaskStorage = TaskStorage()
    private let networkManager = NetworkManager()
    
    func load(then completion: @escaping ([Task]) -> Void) {
        networkManager.getAllTasks {  result in
            switch result {
            case .success(let data):
                tasks.overwrite(tasks: data)
                debugPrint(data)
                completion(data)
            case .failure(let error): print(error.localizedDescription)
            }
        }
    }
    
    func add(state: TaskStatus, title: String, content: String, completion: @escaping (Task) -> Void) {
        let newTask = Task(status: state, title: title, content: content)
        
        networkManager.post(item: newTask) { result in
            switch result {
            case .success(let data):
                tasks.add(task: data)
                completion(data)
            case .failure(let error): print(error.localizedDescription)
            }
        }
    }
    
    func delete(id: Int, completion: @escaping (Int) -> Void) {
        networkManager.delete(id: id) { result in
            switch result {
            case .success(let id):
                tasks.delete(id: id)
                completion(id)
            case .failure(let error): print(error.localizedDescription)
            }
        }
    }
    
    func update(id: Int, state: TaskStatus, title: String, content: String, completion: @escaping (Task) -> Void) {
        let newTask = Task(status: state, title: title, content: content, id: id)
        
        networkManager.patch(item: newTask) { result in
            switch result {
            case .success(let data):
                tasks.update(task: data)
                completion(data)
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

