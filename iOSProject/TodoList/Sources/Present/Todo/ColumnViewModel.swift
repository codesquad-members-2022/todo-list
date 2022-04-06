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
        let addCard = PassthroughSubject<Card, Never>()
        let editCard = PassthroughSubject<Card, Never>()
    }
    
    struct State {
        let loadedColumn = PassthroughSubject<Void, Never>()
        let insertedCard = PassthroughSubject<Int, Never>()
        let movedCard = PassthroughSubject<(Card, Card.Status), Never>()
        let deletedCard = PassthroughSubject<Int, Never>()
        let reloadCard = PassthroughSubject<Int, Never>()
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
            .map { self.todoRepository.moveCard($0, $1)}
            .switchToLatest()
            .sink {
                switch $0 {
                case .success((let cardIndex, let toColumn)):
                    let card = self.cards.remove(at: cardIndex)
                    self.state.deletedCard.send(cardIndex)
                    self.state.movedCard.send((card, toColumn))
                case .failure(let error):
                    print(error)
                }
            }.store(in: &cancellables)
        
        action.deleteCard
            .map { self.todoRepository.deleteCard($0)}
            .switchToLatest()
            .sink {
                switch $0 {
                case .success(let index):
                    self.cards.remove(at: index)
                    self.state.deletedCard.send(index)
                case .failure(let error):
                    print(error)
                }
            }.store(in: &cancellables)
        
        action.addCard
            .sink {
                self.cards.insert($0, at: 0)
                self.state.insertedCard.send(0)
            }.store(in: &cancellables)
        
        action.editCard
            .sink { card in
                self.cards[card.orderIndex] = card
                self.state.reloadCard.send(card.orderIndex)
            }.store(in: &cancellables)
    }
}
