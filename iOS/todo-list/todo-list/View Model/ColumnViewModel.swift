//
//  ColumnViewModel.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/11.
//

import Foundation

struct ColumnViewModel {
    var list = Observable([TaskCellViewModel]())
    let state: TaskStatus
    private let taskManager: TaskManager
    
    init(state: TaskStatus, taskManager: TaskManager) {
        self.state = state
        self.taskManager = taskManager
    }
    
    var count: Int {
        list.value.count
    }
    
    subscript(index: Int) -> TaskCellViewModel? {
        guard index < list.value.count else { return nil }
        return list.value[index]
    }
    
    func load() {
        taskManager.load { tasks in
            let taskVM = tasks.filter { $0.status == state }.map {
                TaskCellViewModel(id: $0.id, title: $0.title, content: $0.content, device: $0.device)
            }
            
            self.list.value = taskVM
        }
    }
    
    func add(title: String, content: String) {
        taskManager.add(state: state, title: title, content: content) { newTask in
            let newTaskViewModel = TaskCellViewModel(id: newTask.id, title: newTask.title, content: newTask.content, device: newTask.device)
            self.list.value.append(newTaskViewModel)
        }
    }
    
    func delete(id: Int) {
        //
    }
    
    func reorder(from: Int, to: Int) {
        //
    }
}

struct TaskCellViewModel {
    let id: Int
    let title: String
    let content: String
    let device: Device
}
