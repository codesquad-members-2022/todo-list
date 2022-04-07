//
//  TodoRepository.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine

protocol TodoRepository {
    func loadColumn(_ columnType: Card.Status) -> AnyPublisher<Result<[Card], SessionError>, Never>
    func moveCard(_ cardId: Int, from: Card.Status, to: Card.Status) -> AnyPublisher<Result<(Int, Card.Status), SessionError>, Never>
    func deleteCard(_ cardId: Int) -> AnyPublisher<Result<Int, SessionError>, Never>
    func editCard(_ cardId: Int, title: String, body: String) -> AnyPublisher<Result<Card, SessionError>, Never>
    func addCard(title: String, body: String, column: Card.Status) -> AnyPublisher<Result<Card, SessionError>, Never>
}
