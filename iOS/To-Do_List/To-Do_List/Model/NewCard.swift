//
//  PostCard.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/12.
//

import Foundation
//Post:
//{NewCard :
//    { author : “Kai” ,
//      title :
//      content: “”
//      boardtype : “todo”
//      action : “Create” ….
//      lastmodifiedAt: “2022-12-31 00:00:00”
//    }
//}

struct NewCard :Codable {
    let Card : CardInfo
}

struct CardInfo : Codable {
    let writer : String?
    let position : Int?
    let title : String
    let content : String
    let cardType: String
    let memberId : Int?
    
    func body() -> [String:Any]? {
        return ["writer":writer,"position":position, "title":title, "content":content, "cardType":cardType, "memberId":memberId]
    }
    
}
