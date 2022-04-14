//
//  PostCard.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/12.
//

import Foundation

struct NewCard : Codable {
    let id : Int?
    let writer : String?
    let title : String
    let content : String
    let cardType: String
    
    func body() -> [String:Any]? {
        return ["id": id,"writer":writer,"title":title,"content":content,"cardType":cardType]
    }
}

