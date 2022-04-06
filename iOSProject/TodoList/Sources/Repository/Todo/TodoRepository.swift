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
}
