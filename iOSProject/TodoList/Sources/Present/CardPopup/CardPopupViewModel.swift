//
//  CardPopupViewModel.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/06.
//

import Combine

protocol CardPopupViewModelBinding {
    var action: CardPopupViewModelAction { get }
    var state: CardPopupViewModelState { get }
}

struct CardPopupViewModelAction {
    let loadModel = PassthroughSubject<Void, Never>()
    let changeText = PassthroughSubject<(String, String), Never>()
    
    let tappedAddButton = PassthroughSubject<(String, String), Never>()
    let tappedEditButton = PassthroughSubject<(String, String), Never>()
}

struct CardPopupViewModelState {
    let loadedModel = PassthroughSubject<CardPopupData, Never>()
    let isEnableButton = PassthroughSubject<Bool, Never>()
    
    let addedCard = PassthroughSubject<Card, Never>()
    let editedCard = PassthroughSubject<Card, Never>()
}

struct CardPopupData {
    let id: Int?
    let title: String
    let body: String
    let column: Column.ColumnType
    
    init(card: Card, columnType: Column.ColumnType) {
        id = card.id
        title = card.title
        body = card.content
        column = columnType
    }
    
    init(columnType: Column.ColumnType) {
        id = nil
        title = ""
        body = ""
        column = columnType
    }
}

typealias CardPopupViewModelProtocol = CardPopupViewModelBinding

final class CardPopupViewModel: CardPopupViewModelProtocol {
    private var cancellables = Set<AnyCancellable>()
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    
    private let popupData: CardPopupData
    
    let action = CardPopupViewModelAction()
    let state = CardPopupViewModelState()
    
    init(popupData: CardPopupData) {
        self.popupData = popupData
        
        action.loadModel
            .map{ self.popupData }
            .sink(receiveValue: self.state.loadedModel.send(_:))
            .store(in: &cancellables)
        
        action.changeText
            .map { title, body in
                let equalBaseText = self.popupData.title == title && self.popupData.body == body
                let isEmpty = title.isEmpty || body.isEmpty
                return (!equalBaseText && !isEmpty)
            }
            .sink(receiveValue: self.state.isEnableButton.send(_:))
            .store(in: &cancellables)
        
        let requestAddCard = action.tappedAddButton
            .map { title, body in self.todoRepository.addCard(title: title, body: body, column: self.popupData.column) }
            .switchToLatest()
            .share()
        
        requestAddCard
            .compactMap{ $0.value}
            .sink(receiveValue: state.addedCard.send(_:))
            .store(in: &cancellables)
        
        let requestEditCard = action.tappedEditButton
            .compactMap{ title, body in
                guard let id = self.popupData.id else { return nil }
                return (id, title, body)
            }
            .map { self.todoRepository.editCard($0, title: $1, body: $2) }
            .switchToLatest()
            .share()
        
        requestEditCard
            .compactMap{ $0.value}
            .sink(receiveValue: state.editedCard.send(_:))
            .store(in: &cancellables)
        
        Publishers
            .Merge(
                requestAddCard.compactMap{ $0.error },
                requestEditCard.compactMap{ $0.error }
            )
            .sink { error in
                
            }.store(in: &cancellables)
    }
}
