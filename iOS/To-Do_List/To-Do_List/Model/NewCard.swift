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
    let author : String
    let title : String
    let content : String
    let boardType : BoardType
    let action: Action
    let lastmodifiedAt : String
}
