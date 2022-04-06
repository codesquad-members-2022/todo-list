//
//  Card.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

struct Card: Codable {
    let title: String
    let body: String
    let caption: String
    let orderIndex: Int
    
    enum CodingKeys: String, CodingKey {
        case title
        case body = "content"
        case caption = "author_system"
        case orderIndex = "order_index"
    }
}
