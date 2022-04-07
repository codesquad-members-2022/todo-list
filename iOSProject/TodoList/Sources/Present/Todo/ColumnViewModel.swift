//
//  TodoViewModel.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Foundation
import Combine
import UIKit

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
        let loadColumn = PassthroughSubject<Void, Never>()
        let tappedAddButton = PassthroughSubject<Void, Never>()
        let tappedMoveCardButton = PassthroughSubject<Int, Never>()
        let tappedEditButton = PassthroughSubject<Int, Never>()
        let tappedDeleteButton = PassthroughSubject<Int, Never>()
        
        let addCard = PassthroughSubject<Card, Never>()
        let editCard = PassthroughSubject<Card, Never>()
    }
    
    struct State {
        let loadedColumn = PassthroughSubject<(Int, Card.Status), Never>()
        let insertedCard = PassthroughSubject<Int, Never>()
        let movedCard = PassthroughSubject<(Card, Card.Status), Never>()
        let deletedCard = PassthroughSubject<Int, Never>()
        let reloadCard = PassthroughSubject<Int, Never>()
        
        let showCardPopup = PassthroughSubject<CardPopupData, Never>()
    }
    
    private var cancellables = Set<AnyCancellable>()
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    private var sortIds: [Int] = []
    private var cards: [Int:Card] = [:]
    private let columnType: Card.Status
    
    let action = Action()
    let state = State()
    
    var cardCount: Int {
        cards.count
    }
    
    subscript(index: Int) -> Card? {
        cards[sortIds[index]]
    }
    
    init(columnType: Card.Status) {
        self.columnType = columnType
        
        action.loadColumn
            .map { _ in self.todoRepository.loadColumn(columnType) }
            .switchToLatest()
            .sink {
                switch $0 {
                case .success(let column):
                    self.sortIds = column.map{ $0.id }
                    self.cards = column.reduce(into: [Int:Card]()) { $0[$1.id] = $1}
                    self.state.loadedColumn.send((self.cards.count, self.columnType))
                case .failure(let error):
                    print(error)
                }
            }.store(in: &cancellables)
        
        action.tappedAddButton
            .map { CardPopupData(id: nil, title: "", body: "", column: self.columnType) }
            .sink(receiveValue: self.state.showCardPopup.send(_:))
            .store(in: &cancellables)
        
        action.tappedMoveCardButton
            .map { self.todoRepository.moveCard(self.sortIds[$0], from: self.columnType, to: .done )}
            .switchToLatest()
            .sink {
                switch $0 {
                case .success((let cardId, let toColumn)):
                    guard let cardIndex = self.sortIds.firstIndex(of: cardId),
                          let removedCard = self.cards.removeValue(forKey: cardId) else {
                        return
                    }
                    self.sortIds.remove(at: cardIndex)
                    self.state.deletedCard.send(cardIndex)
                    self.state.movedCard.send((removedCard, toColumn))
                case .failure(let error):
                    print(error)
                }
            }.store(in: &cancellables)
        
        action.tappedEditButton
            .map {
                let cardId = self.sortIds[$0]
                let card = self.cards[cardId]
                return CardPopupData(id: cardId, title: card?.title ?? "", body: card?.body ?? "", column: self.columnType)
            }
            .sink(receiveValue: self.state.showCardPopup.send(_:))
            .store(in: &cancellables)
        
        action.tappedDeleteButton
            .map { self.todoRepository.deleteCard(self.sortIds[$0]) }
            .switchToLatest()
            .sink {
                switch $0 {
                case .success(let cardId):
                    guard let index = self.sortIds.firstIndex(of: cardId) else {
                        return
                    }
                    self.cards.removeValue(forKey: cardId)
                    self.sortIds.remove(at: index)
                    self.state.deletedCard.send(index)
                case .failure(let error):
                    print(error)
                }
            }.store(in: &cancellables)
        
        action.addCard
            .sink { card in
                self.sortIds.insert(card.id, at: 0)
                self.cards[card.id] = card
                self.state.insertedCard.send(0)
            }.store(in: &cancellables)
        
        action.editCard
            .sink { card in
                guard let index = self.sortIds.firstIndex(of: card.id) else {
                    return
                }
                self.cards[card.id] = card
                self.state.reloadCard.send(index)
            }.store(in: &cancellables)
    }
}
