//
//  ServerAPI.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/13.
//

import Foundation

enum ServerAPI {
    static let baseURL = "http://3.39.127.191:8080/list"
    
    case get
    case getLog
    case post
    case delete(id: String)
    case patch(id: String)

    var url: URL? {
        switch self {
        case .get:
            return URL(string: Self.baseURL)
        case .getLog:
            return URL(string: "\(Self.baseURL)/menu")
        case .post:
            return URL(string: Self.baseURL)
        case .delete(let id):
            return URL(string: "\(Self.baseURL)/\(id)")
        case .patch(let id):
            return URL(string: "\(Self.baseURL)/\(id)")
        }
    }
    
}
