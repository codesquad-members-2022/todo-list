//
//  TodoViewModel.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Foundation
import Combine

protocol ColumnViewModelBinding {
    var action: ColumnViewModel.Action { get }
    var state: ColumnViewModel.State { get }
}

class ColumnViewModel: ColumnViewModelBinding {
    struct Action {
        let loadColumn = PassthroughSubject<Void, Never>()
        let newCard = PassthroughSubject<(String, String), Never>()
    }
    
    struct State {
        let loadedColumn = CurrentValueSubject<Column?, Never>(nil)
    }
    
    var cancellables = Set<AnyCancellable>()
    let action = Action()
    let state = State()
    
    let todoRepository: TodoRepositoryImpl = TodoRepositoryImpl()
    
    init() {
        action.loadColumn
            .map { self.todoRepository.loadColumn() }
            .switchToLatest()
            .sink(receiveCompletion: { error in
                print(error)
            }, receiveValue: { result in
                print(result)
            }).store(in: &cancellables)
        
        action.newCard
            .sink { _ in
                print("asdfasdf")
            }.store(in: &cancellables)
    }
}
