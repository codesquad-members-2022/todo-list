//
//  TodoRepositoryImpl.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine

class TodoRepositoryImpl: NetworkRepository<TodoTarget>, TodoRepository {
    func loadColumn() -> AnyPublisher<Result<[Card], SessionError>, Never> {
        self.request(.loadColumn, isSucccess: true)
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
    
    func moveCard(_ index: Int, _ toColumn: Card.Status) -> AnyPublisher<Result<(Int, Card.Status), SessionError>, Never> {
        self.request(.moveCard(index, toColumn: toColumn), isSucccess: true)
            .map { result in
                switch result {
                case .success:
                    return .success((index, toColumn))
                case .failure(let error):
                    return .failure(error)
                }
            }.eraseToAnyPublisher()
    }
    
    func deleteCard(_ index: Int) -> AnyPublisher<Result<Int, SessionError>, Never> {
        self.request(.deleteCard(index), isSucccess: true)
            .map { result in
                switch result {
                case .success:
                    return .success((index))
                case .failure(let error):
                    return .failure(error)
                }
            }.eraseToAnyPublisher()
    }
    func editCard(_ index: Int, title: String, body: String) -> AnyPublisher<Result<Card, SessionError>, Never> {
        self.request(.editCard(index, title: title, body: body), isSucccess: true)
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
    
    func addCard(title: String, body: String) -> AnyPublisher<Result<Card, SessionError>, Never> {
        self.request(.addCard(title: title, body: body), isSucccess: true)
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
}
