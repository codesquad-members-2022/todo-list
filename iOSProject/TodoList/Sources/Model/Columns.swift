//
//  Columns.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/09.
//

import Foundation

struct Columns: Decodable {
    let columns: [Column]
}

struct Column: Decodable {
    let type: ColumnType
    let cards: [Card]
    
    enum CodingKeys: String, CodingKey {
        case cards
        case type = "columnType"
    }
}

extension Column {
    enum ColumnType: String, CaseIterable, Codable {
        case todo = "to_do"
        case progress = "in_progress"
        case done = "done"
    }
}
