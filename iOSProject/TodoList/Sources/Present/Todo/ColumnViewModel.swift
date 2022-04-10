//
//  TodoViewModel.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Combine

protocol ColumnViewModelBinding {
    var action: ColumnViewModel.Action { get }
    var state: ColumnViewModel.State { get }
}

protocol ColumnViewModelProperty {
    subscript(index: Int) -> Card? { get }
    var cardCount: Int { get }
}
typealias ColumnViewModelProtocol = ColumnViewModelBinding & ColumnViewModelProperty

class ColumnViewModel: ColumnViewModelProtocol {
    struct Action {
        let viewDidLoad = PassthroughSubject<Void, Never>()
        let tappedAddButton = PassthroughSubject<Void, Never>()
        let tappedMoveCardButton = PassthroughSubject<Int, Never>()
        let tappedEditButton = PassthroughSubject<Int, Never>()
        let tappedDeleteButton = PassthroughSubject<Int, Never>()
        
        let addCard = PassthroughSubject<(Card, Int), Never>()
        let editCard = PassthroughSubject<Card, Never>()
    }
    
    struct State {
        let loadedColumn = PassthroughSubject<Column.ColumnType, Never>()
        let insertedCard = PassthroughSubject<Int, Never>()
        let movedCard = PassthroughSubject<(Card, Column.ColumnType), Never>()
        let deletedCard = PassthroughSubject<Int, Never>()
        let reloadCard = PassthroughSubject<Int, Never>()
        
        let showCardPopup = PassthroughSubject<CardPopupData, Never>()
    }
    
    private var cancellables = Set<AnyCancellable>()
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    private var cards: [Card]
    private let columnType: Column.ColumnType
    
    let action = Action()
    let state = State()
    
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
            .map { CardPopupData(id: nil, title: "", body: "", column: self.columnType) }
            .sink(receiveValue: self.state.showCardPopup.send(_:))
            .store(in: &cancellables)
        
        action.tappedEditButton
            .map { index in
                let card = self.cards[index]
                return CardPopupData(id: card.id, title: card.title, body: card.content, column: self.columnType)
            }
            .sink(receiveValue: self.state.showCardPopup.send(_:))
            .store(in: &cancellables)
        
        let requestMoveCard = action.tappedMoveCardButton
            .map { index in self.todoRepository.moveCard(self.cards[index].id, to: .done, index: 0) }
            .switchToLatest()
            .share()
        
        requestMoveCard
            .compactMap{ $0.value }
            .sink { cardId, toColumn, toIndex in
                guard let card = self.cards.first(where: { $0.id == cardId}),
                      let index = self.cards.firstIndex(of: card) else {
                    return
                }
                self.cards.remove(at: index)
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
            .sink { cardId in
                guard let card = self.cards.first(where: { $0.id == cardId}),
                      let index = self.cards.firstIndex(of: card) else {
                    return
                }
                self.cards.remove(at: index)
                self.state.deletedCard.send(index)
            }
            .store(in: &cancellables)
        
        Publishers
            .Merge(
                requestMoveCard.compactMap{ $0.error },
                requestDeleateCard.compactMap{ $0.error }
            )
            .sink { error in
                
            }.store(in: &cancellables)

        action.addCard
            .sink { card, index in
                self.cards.insert(card, at: index)
                self.state.insertedCard.send(0)
            }.store(in: &cancellables)

        action.editCard
            .sink { newCard in
                guard let card = self.cards.first(where: { $0.id == newCard.id}),
                      let index = self.cards.firstIndex(of: card) else {
                    return
                }
                self.cards[index] = newCard
                self.state.reloadCard.send(index)
            }.store(in: &cancellables)
    }
}
