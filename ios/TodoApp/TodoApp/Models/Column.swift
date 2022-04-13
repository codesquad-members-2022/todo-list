//
//  Column.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/12.
//

import Foundation

struct Column: Codable {
    let id: Int
    let name: String
    let count: Int

    enum CodingKeys: String, CodingKey {
        case id = "columnId"
        case name
        case count = "cardCount"
    }
}
