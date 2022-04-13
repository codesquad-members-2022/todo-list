//
//  PostCard.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/12.
//

import Foundation

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
