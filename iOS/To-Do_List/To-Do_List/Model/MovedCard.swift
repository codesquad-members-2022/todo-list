//
//  MovedCard.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/15.
//

import Foundation
struct MovedCard : Codable{
    
    let id : Int
    let originPosition : Int
    let goalPosition : Int
    let originCardType :String
    let goalCardType : String

    func body() -> [String:Any]? {
        return ["originPosition":originPosition,"goalPosition":goalPosition,"originCardType":originCardType,"goalCardType":goalCardType]
    }
}
