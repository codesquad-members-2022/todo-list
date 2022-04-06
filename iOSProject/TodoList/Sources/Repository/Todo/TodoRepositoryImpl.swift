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
    
    func moveCard(cardIndex: Int, toColumn: Card.Status) -> AnyPublisher<Result<(Int, Card.Status), SessionError>, Never> {
        self.request(.moveCard(cardIndex: cardIndex, toColumn: toColumn), isSucccess: true)
            .map { result in
                switch result {
                case .success:
                    return .success((cardIndex, toColumn))
                case .failure(let error):
                    return .failure(error)
                }
            }.eraseToAnyPublisher()
    }
}
