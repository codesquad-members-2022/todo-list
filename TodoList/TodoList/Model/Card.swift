//
//  Card.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/07.
//

import Foundation

struct Card: Codable{
    private(set) var id: Int?
    private(set) var section: State
    private(set) var title: String
    private(set) var content: String
    private(set) var createdDate: Date?
    private(set) var userID: String
    
    enum State: Int, Codable{
        case todo = 1
        case doing = 2
        case done = 3
    }
    
    enum CodingKeys: String, CodingKey {
        case id
        case section
        case title
        case content
        case createdDate = "created_at"
        case userID = "userId"
    }
    
    init(section: Int, title: String, content: String, userID: String){
        self.id = nil
        self.section = State(rawValue: section) ?? .todo
        self.title = title
        self.content = content
        self.createdDate = nil
        self.userID = userID
    }
    
    mutating func changeId(id: Int){
        self.id = id
    }
    
    mutating func changeDate(date: Date){
        self.createdDate = date
    }
}
