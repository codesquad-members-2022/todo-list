//
//  TodoViewModel.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Combine

struct ColumnViewModelAction {
    let viewDidLoad = PassthroughSubject<Void, Never>()
    let tappedAddButton = PassthroughSubject<Void, Never>()
    let tappedMoveDoneColumnButton = PassthroughSubject<Int, Never>()
    let tappedEditButton = PassthroughSubject<Int, Never>()
    let tappedDeleteButton = PassthroughSubject<Int, Never>()
    
    let dropCard = PassthroughSubject<(DragCard, Int), Never>()
    
    let addCard = PassthroughSubject<(Card, Int), Never>()
    let editCard = PassthroughSubject<Card, Never>()
    
    let finishDropedCard = PassthroughSubject<Int, Never>()
}

struct ColumnViewModelState {
    let loadedColumn = PassthroughSubject<Column.ColumnType, Never>()
    let insertedCard = PassthroughSubject<Int, Never>()
    let movedCard = PassthroughSubject<(Card, Column.ColumnType), Never>()
    let deletedCard = PassthroughSubject<Int, Never>()
    let reloadCard = PassthroughSubject<Int, Never>()
    
    let notifiyFinishDropedCard = PassthroughSubject<(Int, Column.ColumnType), Never>()
    let showCardPopup = PassthroughSubject<CardPopupData, Never>()
}

protocol ColumnViewModelBinding {
    var action: ColumnViewModelAction { get }
    var state: ColumnViewModelState { get }
}

protocol ColumnViewModelProperty {
    subscript(index: Int) -> Card? { get }
    var cardCount: Int { get }
    var columnType: Column.ColumnType { get }
}

typealias ColumnViewModelProtocol = ColumnViewModelBinding & ColumnViewModelProperty

class ColumnViewModel: ColumnViewModelProtocol {
    private var cancellables = Set<AnyCancellable>()
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    private var cards: [Card]
    let columnType: Column.ColumnType
    
    let action = ColumnViewModelAction()
    let state = ColumnViewModelState()
    
    var cardCount: Int {
        cards.count
    }
    
    subscript(index: Int) -> Card? {
        cards[index]
    }
    
    init(column: Column) {
        columnType = column.type
        cards = column.cards
        
        action.viewDidLoad
            .sink {
                self.state.loadedColumn.send(self.columnType)
            }.store(in: &cancellables)
        
        action.tappedAddButton
            .map { CardPopupData(columnType: self.columnType) }
            .sink(receiveValue: self.state.showCardPopup.send(_:))
            .store(in: &cancellables)
        
        action.tappedEditButton
            .map { index in CardPopupData(card: self.cards[index], columnType: self.columnType)}
            .sink(receiveValue: self.state.showCardPopup.send(_:))
            .store(in: &cancellables)
        
        let requestMoveDoneColumn = action.tappedMoveDoneColumnButton
            .map { index in self.todoRepository.moveCard(self.cards[index], from: self.columnType, to: .done, index: 0) }
            .switchToLatest()
            .share()
        
        requestMoveDoneColumn
            .compactMap{ $0.value }
            .compactMap{ card, _, _, _ in self.cards.firstIndex(where: { $0.id == card.id}) }
            .sink { index in
                let card = self.cards.remove(at: index)
                self.state.deletedCard.send(index)
                self.state.movedCard.send((card, .done))
            }
            .store(in: &cancellables)
        
        let requestDeleateCard = action.tappedDeleteButton
            .map{ index in self.todoRepository.deleteCard(self.cards[index].id) }
            .switchToLatest()
            .share()
        
        requestDeleateCard
            .compactMap{ $0.value }
            .compactMap{ cardId in self.cards.firstIndex(where: { $0.id == cardId})}
            .sink { index in
                self.cards.remove(at: index)
                self.state.deletedCard.send(index)
            }
            .store(in: &cancellables)
        
        Publishers
            .Merge(
                requestMoveDoneColumn.compactMap{ $0.error },
                requestDeleateCard.compactMap{ $0.error }
            )
            .sink { error in
                print(error)
            }.store(in: &cancellables)

        action.addCard
            .sink { card, index in
                self.cards.insert(card, at: index)
                self.state.insertedCard.send(index)
            }.store(in: &cancellables)

        action.editCard
            .sink { newCard in
                guard let index = self.cards.firstIndex(where: { $0.id == newCard.id}) else {
                    return
                }
                self.cards[index] = newCard
                self.state.reloadCard.send(index)
            }.store(in: &cancellables)
        
        let requestDropCard = action.dropCard
            .compactMap{ dropCard, index -> (Card, Column.ColumnType, Column.ColumnType, Int)? in
                guard let card = dropCard.card else { return nil }
                return (card, dropCard.fromColumn, self.columnType, index)
            }
            .map { (card, from, to, index) in self.todoRepository.moveCard(card, from: from, to: to, index: index) }
            .switchToLatest()
            .share()
        
        requestDropCard
            .compactMap{ $0.value }
            .sink { card, from, to, index in
                self.cards.insert(card, at: index)
                self.state.insertedCard.send(index)
                self.state.notifiyFinishDropedCard.send((card.id, from))
            }
            .store(in: &cancellables)
        
        action.finishDropedCard
            .compactMap{ cardId in self.cards.firstIndex(where: { $0.id == cardId})}
            .sink { index in
                self.cards.remove(at: index)
                self.state.deletedCard.send(index)                
            }.store(in: &cancellables)
    }
}
