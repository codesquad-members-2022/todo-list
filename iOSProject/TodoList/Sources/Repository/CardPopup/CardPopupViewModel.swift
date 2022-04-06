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

protocol CardPopupViewModelProperty {
}

class CardPopupViewModel: CardPopupViewModelBinding, CardPopupViewModelProperty {
    struct Action {
        let addCard = PassthroughSubject<(String, String), Never>()
        let editCard = PassthroughSubject<(Int, String, String), Never>()
    }
    
    struct State {
        let addedCard = PassthroughSubject<Card, Never>()
        let editedCard = PassthroughSubject<Card, Never>()
    }
    
    private var cancellables = Set<AnyCancellable>()
    let action = Action()
    let state = State()
    
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    
    init() {
        self.action.addCard
            .map { self.todoRepository.addCard(title: $0, body: $1)}
            .switchToLatest()
            .sink {
                switch $0 {
                case .success(let card):
                    self.state.addedCard.send(card)
                case .failure(let error):
                    print(error)
                }
            }.store(in: &cancellables)
        
        self.action.editCard
            .map { self.todoRepository.editCard($0, title: $1, body: $2)}
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
