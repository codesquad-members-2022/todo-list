//
//  TodoRepository.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Combine

protocol TodoRepository {
    func loadColumns() -> AnyPublisher<ApiResult<[Column], SessionError>, Never>
    func addCard(title: String, body: String, column: Column.ColumnType) -> AnyPublisher<ApiResult<Card, SessionError>, Never>
    func moveCard(_ cardId: Int, to column: Column.ColumnType, index: Int) -> AnyPublisher<ApiResult<(Int, Column.ColumnType, Int), SessionError>, Never>
    func editCard(_ cardId: Int, title: String, body: String) -> AnyPublisher<ApiResult<Card, SessionError>, Never>
    func deleteCard(_ cardId: Int) -> AnyPublisher<ApiResult<Int, SessionError>, Never>
}
