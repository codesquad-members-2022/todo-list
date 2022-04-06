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
        let moveCard = PassthroughSubject<(Int, Card.Status), Never>()
        let deleteCard = PassthroughSubject<Int, Never>()
    }
    
    struct State {
        let loadedColumn = PassthroughSubject<Void, Never>()
        let insertedCard = PassthroughSubject<Int, Never>()
        let movedCard = PassthroughSubject<(Card, Card.Status), Never>()
    }
    
    private var cancellables = Set<AnyCancellable>()
    let action = Action()
    let state = State()
    
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    
    private var cards: [Card] = []
    
    var cardCount: Int {
        cards.count
    }
    
    subscript(index: Int) -> Card? {
        cards[index]
    }
    
    init() {
        action.loadColumn
            .map { _ in self.todoRepository.loadColumn() }
            .switchToLatest()
            .sink {
                switch $0 {
                case .success(let column):
                    self.cards = column
                    self.state.loadedColumn.send()
                case .failure(let error):
                    print(error)
                }
            }.store(in: &cancellables)
        
        action.newCard
            .map{ Card(title: $0, body: $1, caption: "author by iOS", orderIndex: 0)}
            .sink {
                self.cards.insert($0, at: 0)
                self.state.insertedCard.send(0)
            }
            .store(in: &cancellables)
        
        action.moveCard
            .sink { _ in 
                
            }.store(in: &cancellables)
        
        action.deleteCard
            .sink {
                print("deleteCard: \($0)")
            }.store(in: &cancellables)
    }
}
