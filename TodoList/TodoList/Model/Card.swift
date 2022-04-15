//
//  Card.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/07.
//

import Foundation

struct Card: Codable, Equatable{
    private(set) var id: Int
    private(set) var section: State
    private(set) var title: String
    private(set) var content: String
    private(set) var createdDate: String
    private(set) var userId: String?
    
    enum State: Int, Codable{
        case todo = 0
        case doing
        case done
    }
    
    enum CodingKeys: String, CodingKey {
        case id
        case section
        case title
        case content
        case createdDate = "modifiedAt"
        case userId
    }
    
    func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(id, forKey: .id)
        try container.encode(section, forKey: .section)
        try container.encode(title, forKey: .title)
        try container.encode(content, forKey: .content)
        try container.encode(createdDate, forKey: .createdDate)
        try container.encode(userId, forKey: .userId)
    }
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        id = try container.decode(Int.self, forKey: .id)
        section = try container.decode(State.self, forKey: .section)
        title = try container.decode(String.self, forKey: .title)
        content = try container.decode(String.self, forKey: .content)
        createdDate = try container.decode(String.self, forKey: .createdDate)
    }
    
    init(section: Int, title: String, content: String, userID: String){
        self.id = 0
        self.section = State(rawValue: section) ?? .todo
        self.title = title
        self.content = content
        self.createdDate = ""
        self.userId = userID
    }
    
    mutating func changeId(id: Int){
        self.id = id
    }
    
    mutating func changeDate(date: String){
        self.createdDate = date
    }
    
    mutating func changeTitle(title: String){
        self.title = title
    }
    
    mutating func changeContent(content: String){
        self.content = content
    }
}
