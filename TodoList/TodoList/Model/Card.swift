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
    private(set) var createdDate: String
    
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
    }
    
    init(section: Int, title: String, content: String, userID: String){
        self.id = 0
        self.section = State(rawValue: section) ?? .todo
        self.title = title
        self.content = content
        self.createdDate = ""
    }
    
    mutating func changeId(id: Int){
        self.id = id
    }
    
    mutating func changeDate(date: String){
        self.createdDate = date
    }
}
