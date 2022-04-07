//
//  CardPopupViewModel.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/06.
//

import Foundation
import Combine

protocol CardPopupViewModelBinding {
    var action: CardPopupViewModel.Action { get }
    var state: CardPopupViewModel.State { get }
}

struct CardPopupData {
    let id: Int?
    let title: String
    let body: String
    let column: Card.Status
}

class CardPopupViewModel: CardPopupViewModelBinding {
    struct Action {
        let loadModel = PassthroughSubject<Void, Never>()
        let changeText = PassthroughSubject<(String, String), Never>()
        
        let tappedAddButton = PassthroughSubject<(String, String), Never>()
        let tappedEditButton = PassthroughSubject<(String, String), Never>()
    }
    
    struct State {
        let loadedModel = PassthroughSubject<CardPopupData, Never>()
        let isEnableButton = PassthroughSubject<Bool, Never>()
        
        let addedCard = PassthroughSubject<Card, Never>()
        let editedCard = PassthroughSubject<Card, Never>()
    }
    
    private var cancellables = Set<AnyCancellable>()
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    
    private let popupData: CardPopupData
    
    let action = Action()
    let state = State()
    
    init(popupData: CardPopupData) {
        self.popupData = popupData
        
        self.action.loadModel
            .map{ self.popupData }
            .sink(receiveValue: self.state.loadedModel.send(_:))
            .store(in: &cancellables)
        
        self.action.changeText
            .map { title, body in
                let equalBaseText = self.popupData.title == title && self.popupData.body == body
                let isEmpty = title.isEmpty || body.isEmpty
                return (!equalBaseText && !isEmpty)
            }
            .sink(receiveValue: self.state.isEnableButton.send(_:))
            .store(in: &cancellables)
        
        self.action.tappedAddButton
            .map { self.todoRepository.addCard(title: $0, body: $1, column: self.popupData.column) }
            .switchToLatest()
            .sink {
                switch $0 {
                case .success(let card):
                    self.state.addedCard.send(card)
                case .failure(let error):
                    print(error)
                }
            }.store(in: &cancellables)
        
        self.action.tappedEditButton
            .compactMap{
                guard let id = self.popupData.id else { return nil }
                return (id, $0, $1)
            }
            .map { self.todoRepository.editCard($0, title: $1, body: $2) }
            .switchToLatest()
            .sink {
                switch $0 {
                case .success(let card):
                    self.state.editedCard.send(card)
                case .failure(let error):
                    print(error)
                }
            }.store(in: &cancellables)
    }
}
