//
//  CardData.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/12.
//

import Foundation

struct CardData: Codable {
    let userId: String
    let title: String
    let content: String
    let sequence: Int
    let status: Int
}
