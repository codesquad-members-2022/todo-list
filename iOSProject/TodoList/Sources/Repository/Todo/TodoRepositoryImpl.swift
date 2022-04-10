//
//  TodoRepositoryImpl.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine

class TodoRepositoryImpl: NetworkRepository<TodoTarget>, TodoRepository {
    func loadColumns() -> AnyPublisher<ApiResult<[Column], SessionError>, Never> {
        request(.loadColumns, isSucccess: true)
            .map { $0.decode([Column].self) }
            .eraseToAnyPublisher()
    }
    
    func addCard(title: String, body: String, column: Column.ColumnType) -> AnyPublisher<ApiResult<Card, SessionError>, Never> {
        request(.addCard(title: title, body: body, column: column), isSucccess: true)
            .map { $0.decode(Card.self) }
            .eraseToAnyPublisher()
    }
    
    func moveCard(_ cardId: Int, to column: Column.ColumnType, index: Int) -> AnyPublisher<ApiResult<(Int, Column.ColumnType, Int), SessionError>, Never> {
        request(.moveCard(cardId, toColumn: column, toIndex: index), isSucccess: true)
            .map { result in result.mapValue((cardId, column, index)) }
            .eraseToAnyPublisher()
    }
    
    func editCard(_ cardId: Int, title: String, body: String) -> AnyPublisher<ApiResult<Card, SessionError>, Never> {
        request(.editCard(cardId, title: title, body: body), isSucccess: true)
            .map { $0.decode(Card.self) }
            .eraseToAnyPublisher()
    }
    
    func deleteCard(_ cardId: Int) -> AnyPublisher<ApiResult<Int, SessionError>, Never> {
        request(.deleteCard(cardId), isSucccess: true)
            .map { result in result.mapValue(cardId) }
            .eraseToAnyPublisher()
    }
}
