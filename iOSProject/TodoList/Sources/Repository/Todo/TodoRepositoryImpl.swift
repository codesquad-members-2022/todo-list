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
    }
}
