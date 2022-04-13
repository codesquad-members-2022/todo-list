//
//  ToDo.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/11.
//

import Foundation

struct Todo: Codable {
    let id: Int
    let title: String
    let content: String
    let createdAt: String
    
    enum CodingKeys: String, CodingKey {
        case id = "cardId"
        case title
        case content
        case createdAt = "createdDate"
    }
}
