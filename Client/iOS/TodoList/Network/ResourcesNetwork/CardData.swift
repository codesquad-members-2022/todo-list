//
//  CardData.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

struct CardData: Codable
{
    var objectId: String
    
    var title: String
    var contents: String
    var creationDate: String
    var index: Int
    var status: Int
    var updateDate: String
}
