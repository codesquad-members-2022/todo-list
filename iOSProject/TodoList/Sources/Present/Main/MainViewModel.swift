//
//  MainViewModel.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/09.
//

import Combine
import Foundation

struct MainViewModelAction {
    let loadColumns = PassthroughSubject<Void, Never>()
    let moveCard = PassthroughSubject<(DragCard, Column.ColumnType, Int), Never>()
}

struct MainViewModelState {
    let loadedColumns = PassthroughSubject<[ColumnViewModelProtocol], Never>()
    let movedCard = PassthroughSubject<(Card, Column.ColumnType, Column.ColumnType, Int), Never>()
}

protocol MainViewModelBinding {
    var action: MainViewModelAction { get }
    var state: MainViewModelState { get }
}

typealias MainViewModelProtocol = MainViewModelBinding

class MainViewModel: MainViewModelProtocol {
    
    private var cancellables = Set<AnyCancellable>()
    private let todoRepository: TodoRepository = TodoRepositoryImpl()
    
    let action = MainViewModelAction()
    let state = MainViewModelState()
    
    init() {
        let requestLoadColumns = action.loadColumns
            .map {
                self.todoRepository.loadColumns()}
            .switchToLatest()
            .share()
        
        requestLoadColumns
            .compactMap{ $0.value }
            .map { columns in
                columns.columns.map { column in ColumnViewModel(column: column)}
            }
            .sink(receiveValue: self.state.loadedColumns.send(_:))
            .store(in: &cancellables)
        
        let requestMoveCard = action.moveCard
            .compactMap{ dropCard, toColumn, toRow -> (Card, Column.ColumnType, Column.ColumnType, Int)? in
                guard let card = dropCard.card else { return nil }
                return (card, dropCard.fromColumn, toColumn, toRow)
            }
            .map { card, from, to, row in self.todoRepository.moveCard(card, from: from, to: to, index: row)}
            .switchToLatest()
            .share()
        
        requestMoveCard
            .compactMap{ $0.value }
            .sink(receiveValue: state.movedCard.send(_:))
            .store(in: &cancellables)
        
        requestLoadColumns
            .compactMap{ $0.error }
            .sink { error in
                print(error)
            }.store(in: &cancellables)
    }
}
