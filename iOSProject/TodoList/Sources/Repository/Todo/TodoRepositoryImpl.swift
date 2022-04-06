//
//  TodoRepositoryImpl.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine

struct ResponseResult: Decodable {
    let result: String
    let status: String
}

class TodoRepositoryImpl: NetworkRepository<TodoTarget>, TodoRepository {
    func loadColumn() -> AnyPublisher<Result<Column, SessionError>, Never> {
        self.request(.loadColumn, isSucccess: true)
    }
}
