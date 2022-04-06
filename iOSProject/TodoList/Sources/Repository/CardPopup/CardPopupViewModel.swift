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
    }
    
    struct State {
    }
    
    private var cancellables = Set<AnyCancellable>()
    let action = Action()
    let state = State()
    
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    
    init() {
        
    }
}
