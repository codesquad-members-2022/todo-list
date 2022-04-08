//
//  EndPoint.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/07.
//

import Foundation

protocol Endpoint {
    var httpMethod: String { get }
    var baseURL: String { get }
    var path: String { get }
    var headers: [String: Any]? { get }
    var body: Encodable? { get }
}

extension Endpoint {
    var url:String {
        return baseURL + path
    }
}

enum EndPointCases:Endpoint {
    
    case getTodoList
    case postTodoList(todoList:Todoitems)
    
    var httpMethod:String {
        switch self {
        case .getTodoList:
            return HTTPMethod.get
        case .postTodoList:
            return HTTPMethod.post
        }
    }
    
    var baseURL:String {
        switch self {
        case .getTodoList:
            return "http://13.125.161.84:8082/"
        case .postTodoList:
            return "http://13.125.161.84:8082/"
        }
    }
    
    var path:String {
        switch self {
        case .getTodoList:
            return "api/read/cards"
        case .postTodoList:
            return "api/read/cards"
        }
    }
    
    var headers: [String:Any]? {
        switch self {
        case .getTodoList:
            return ["Content-Type": "application/json"]
        case .postTodoList:
            return ["Content-Type": "application/json"]
        }
    }
    
    var body:Encodable? {
        switch self {
        case .getTodoList:
            return nil
        case .postTodoList(let todoList):
            return todoList
        }
    }
}
