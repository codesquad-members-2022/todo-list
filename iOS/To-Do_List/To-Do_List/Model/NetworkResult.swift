//
//  NetworkResult.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/14.
//

import Foundation

struct NewTodo:Codable {
    let response:Todo
}

struct NetworkResult:Codable {
    let response:Board
}

extension NetworkResult:Equatable {
    static func == (lhs: NetworkResult, rhs: NetworkResult) -> Bool {
        lhs.response.completedItems == rhs.response.completedItems && lhs.response.progressingItems == rhs.response.progressingItems && lhs.response.todoItems == rhs.response.todoItems
    }
}
