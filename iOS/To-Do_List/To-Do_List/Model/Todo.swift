//
//  NetworkModel.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/06.
//

import Foundation

//TODO: 타입명 변경
struct NetworkResult:Codable {
    let response:Board
}

struct Board:Codable {
    let todoItems:[Todo]
    let progressingItems:[Todo]
    let completedItems:[Todo]
}

struct Todo:Codable,Equatable {
    let id:Int
    let title:String
    let content:String
    let createdAt:String
}

extension NetworkResult:Equatable {
    static func == (lhs: NetworkResult, rhs: NetworkResult) -> Bool {
        lhs.response.self == rhs.response.self
    }
}

extension Board:Equatable {
    static func == (lhs: Board, rhs: Board) -> Bool {
        lhs.todoItems == rhs.todoItems && lhs.completedItems == rhs.completedItems && lhs.progressingItems ==  rhs.progressingItems
    }
}
