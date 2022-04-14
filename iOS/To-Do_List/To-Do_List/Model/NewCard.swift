//
//  PostCard.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/12.
//

import Foundation

struct NewCard : CardDisplayable, Codable {
    let id : Int?
    let writer : String?
    let title : String
    let content : String
    let cardType: String
    let memberId: Int
    let maxPositionNumber: Int
    
    func body() -> [String:Any]? {
        return ["id": id,"writer":writer,"title":title,"content":content,"cardType":cardType , "memberId":memberId, "maxPositionNumber": maxPositionNumber]
    }
}
