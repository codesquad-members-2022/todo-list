//
//  TodoListViewModel.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/13.
//

import Foundation

protocol TodoListViewModelProtocol {
    func fetchData()
    func remove(at index: Int)
    func getHeaderTitle() -> String
    var onUpdate: () -> Void { get }
    var count: Int { get }
}

final class TodoListViewModel: TodoListViewModelProtocol {
    private let repository: TodoRepositoryProtocol
    private let column: Column
    private var models = [Todo]()
    
    subscript(index: Int) -> Todo? {
        get {
            guard index < self.models.count && index >= self.models.startIndex else {
                return nil
            }
            
            return self.models[index]
        }
    }
    
    var onUpdate: () -> Void = {}
    
    var count: Int {
        return self.models.count
    }
    
    init(entity column: Column, repository: TodoRepositoryProtocol) {
        self.column = column
        self.repository = repository
    }
    
    func remove(at index: Int) {
        // TODO: 서버에 삭제 요청
        self.models.remove(at: index)
    }
    
    func fetchData() {
        self.repository.fetchTodo(from: self.column) { models in
            self.models = models
            self.onUpdate()
        }
    }
    
    func getHeaderTitle() -> String {
        return self.column.name
    }
}
