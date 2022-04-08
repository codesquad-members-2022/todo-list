//
//  TodoRepositoryImpl.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine

class TodoRepositoryImpl: NetworkRepository<TodoTarget>, TodoRepository {
    func loadColumn(_ column: Card.Column) -> AnyPublisher<Result<[Card], SessionError>, Never> {
        request(.loadColumn(column), isSucccess: true)
            .map { result in
                switch result {
                case .success(let data):
                    guard let cards = try? JSONDecoder().decode([Card].self, from: data) else {
                        return .failure(.pasingError)
                    }
                    return .success(cards)
                case .failure(let error):
                    return .failure(error)
                }
            }.eraseToAnyPublisher()
    }
    
    func addCard(title: String, body: String, column: Card.Column) -> AnyPublisher<Result<Card, SessionError>, Never> {
        request(.addCard(title: title, body: body, column: column), isSucccess: true)
            .map { result in
                switch result {
                case .success(let data):
                    guard let card = try? JSONDecoder().decode(Card.self, from: data) else {
                        return .failure(.pasingError)
                    }
                    return .success(card)
                case .failure(let error):
                    return .failure(error)
                }
            }.eraseToAnyPublisher()
    }
    
    func moveCard(_ cardId: Int, from: Card.Column, to: Card.Column) -> AnyPublisher<Result<(Int, Card.Column), SessionError>, Never> {
        request(.moveCard(cardId, fromColumn: from, toColumn: to), isSucccess: true)
            .map { result in
                switch result {
                case .success:
                    return .success((cardId, to))
                case .failure(let error):
                    return .failure(error)
                }
            }.eraseToAnyPublisher()
    }
    
    func editCard(_ cardId: Int, title: String, body: String) -> AnyPublisher<Result<Card, SessionError>, Never> {
        request(.editCard(cardId, title: title, body: body), isSucccess: true)
            .map { result in
                switch result {
                case .success(let data):
                    guard let card = try? JSONDecoder().decode(Card.self, from: data) else {
                        return .failure(.pasingError)
                    }
                    return .success(card)
                case .failure(let error):
                    return .failure(error)
                }
            }.eraseToAnyPublisher()
    }
    
    func deleteCard(_ cardId: Int) -> AnyPublisher<Result<Int, SessionError>, Never> {
        request(.deleteCard(cardId), isSucccess: true)
            .map { result in
                switch result {
                case .success:
                    return .success(cardId)
                case .failure(let error):
                    return .failure(error)
                }
            }.eraseToAnyPublisher()
    }
}
