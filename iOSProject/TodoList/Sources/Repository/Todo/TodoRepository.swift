//
//  TodoRepository.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Combine

protocol TodoRepository {
    func loadColumn(_ columnType: Card.Column) -> AnyPublisher<Result<[Card], SessionError>, Never>
    func moveCard(_ cardId: Int, from: Card.Column, to: Card.Column) -> AnyPublisher<Result<(Int, Card.Column), SessionError>, Never>
    func deleteCard(_ cardId: Int) -> AnyPublisher<Result<Int, SessionError>, Never>
    func editCard(_ cardId: Int, title: String, body: String) -> AnyPublisher<Result<Card, SessionError>, Never>
    func addCard(title: String, body: String, column: Card.Column) -> AnyPublisher<Result<Card, SessionError>, Never>
}
