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

protocol ColumnViewModelProperty {
    subscript(index: Int) -> Card? { get }
    var cardCount: Int { get }
}

class ColumnViewModel: ColumnViewModelBinding, ColumnViewModelProperty {
    struct Action {
        let loadColumn = PassthroughSubject<Card.Status, Never>()
        let newCard = PassthroughSubject<(String, String), Never>()
        let moveCard = PassthroughSubject<Int, Never>()
        let deleteCard = PassthroughSubject<Int, Never>()
    }
    
    struct State {
        let loadedColumn = CurrentValueSubject<[Card]?, Never>(nil)
    }
    
    private var cancellables = Set<AnyCancellable>()
    let action = Action()
    let state = State()
    
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    
    var cardCount: Int {
        state.loadedColumn.value?.count ?? 0
    }
    
    subscript(index: Int) -> Card? {
        state.loadedColumn.value?[index]
    }
    
    init() {
        action.loadColumn
            .map { _ in self.todoRepository.loadColumn() }
            .switchToLatest()
            .sink {
                switch $0 {
                case .success(let column):
                    self.state.loadedColumn.send(column)
                case .failure(let error):
                    print(error)
                }
            }.store(in: &cancellables)
        
        action.newCard
            .sink { _ in
                print("asdfasdf")
            }.store(in: &cancellables)
        
        action.moveCard
            .sink {
                print("moveCard: \($0)")
            }.store(in: &cancellables)
        
        action.deleteCard
            .sink {
                print("deleteCard: \($0)")
            }.store(in: &cancellables)
    }
}
