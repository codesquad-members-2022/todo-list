//
//  PostCard.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/14.
//

import Foundation

struct PostCard: Codable{
    private(set) var section: Int
    private(set) var title: String
    private(set) var content: String
    private(set) var userId: String
    
    init(section: Int, title: String, content: String, userID: String){
        self.section = section
        self.title = title
        self.content = content
        self.userId = userID
    }
}
