//
//  NetworkModel.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/06.
//

import Foundation

//TODO: 타입명 변경
struct Todoitems:Codable {
    let response:Board
}

struct Board:Codable {
    let todoItems:[Todo]
    let progressingItems:[Todo]
    let completedItems:[Todo]
}

struct Todo:Codable {
    let id:Int
    let title:String
    let content:String
    let createdAt:String
    let lastModifiedAt:String
}

