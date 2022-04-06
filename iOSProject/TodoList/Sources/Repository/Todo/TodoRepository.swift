//
//  TodoRepository.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine

protocol TodoRepository {
    func loadColumn() -> AnyPublisher<Result<[Card], SessionError>, Never>
    func moveCard(_ index: Int, _ toColumn: Card.Status) -> AnyPublisher<Result<(Int, Card.Status), SessionError>, Never>
    func deleteCard(_ index: Int) -> AnyPublisher<Result<Int, SessionError>, Never>
}
