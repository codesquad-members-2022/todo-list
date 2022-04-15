//
//  CardMap.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/12.
//

import Foundation

struct CardMap: Codable {
    var cardMap: TodoList
    
    struct TodoList: Codable {
        var TODO: [CardData]
    }
}
