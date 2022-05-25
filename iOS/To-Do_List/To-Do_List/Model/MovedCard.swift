//
//  MovedCard.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/15.
//

import Foundation

struct MovedCard : Codable{
    let id : Int
    let title : String
    let content : String
    let maxPositionNumber : Int
    let goalCardType : String
    
    
    func body() -> [String:Any]? {
        return ["title":title,
                "content":content,
                "maxPositionNumber":maxPositionNumber,
                "goalCardType":goalCardType
        ]
    }
}
