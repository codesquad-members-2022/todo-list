//
//  Card.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/07.
//

import Foundation

struct Card: Codable{
    private(set) var id: Int
    private(set) var section: State
    private(set) var title: String
    private(set) var content: String
    private(set) var createdDate: Date
    private(set) var userID: String
    
    enum State: String, Codable{
        case todo
        case doing
        case done
    }
    
    enum CodingKeys: String, CodingKey {
        case id
        case section
        case title
        case content
        case createdDate = "created_at"
        case userID = "userId"
    }
}
