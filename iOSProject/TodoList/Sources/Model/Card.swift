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

extension Card {
    enum Status: String, Decodable {
        case todo = "to_do"
        case progress = "in_progress"
        case done = "done"
        
        var titleName: String {
            switch self {
            case .todo:
                return "해야할 일"
            case .progress:
                return "하고 있는 일"
            case .done:
                return "완료한 일"
            }
        }
    }
}
