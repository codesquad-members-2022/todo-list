//
//  TodoRepositoryImpl.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine

class TodoRepositoryImpl: NetworkRepository<TodoTarget>, TodoRepository {
    func loadColumns() -> AnyPublisher<ApiResult<Columns, SessionError>, Never> {
        request(.loadColumns)
            .map { $0.decode(Columns.self) }
            .eraseToAnyPublisher()
    }
    
    func addCard(title: String, body: String, column: Column.ColumnType) -> AnyPublisher<ApiResult<Card, SessionError>, Never> {
        request(.addCard(title: title, body: body, column: column, authorSystem: "iOS"))
            .map { $0.decode(Card.self) }
            .eraseToAnyPublisher()
    }
    
    func moveCard(_ card: Card, from: Column.ColumnType, to: Column.ColumnType, index: Int) -> AnyPublisher<ApiResult<(Card, Column.ColumnType, Column.ColumnType, Int), SessionError>, Never> {
        request(.moveCard(card.id, toColumn: to, toIndex: index), isSucccess: true)
            .map { result in result.mapValue((card, from, to, index)) }
            .eraseToAnyPublisher()
    }
    
    func editCard(_ cardId: Int, title: String, body: String) -> AnyPublisher<ApiResult<Card, SessionError>, Never> {
        request(.editCard(cardId, title: title, body: body))
            .map { $0.decode(Card.self) }
            .eraseToAnyPublisher()
    }
    
    func deleteCard(_ cardId: Int) -> AnyPublisher<ApiResult<Int, SessionError>, Never> {
        request(.deleteCard(cardId))
            .map { result in result.mapValue(cardId) }
            .eraseToAnyPublisher()
    }
    
    func loadLogs() -> AnyPublisher<ApiResult<ActivityLog, SessionError>, Never> {
        request(.loadLogs)
            .map { $0.decode(ActivityLog.self) }
            .eraseToAnyPublisher()
    }
}
