//
//  Columns.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/09.
//

import Foundation

struct Column: Codable {
    let type: ColumnType
    let cards: [Card]
}

extension Column {
    enum ColumnType: String, CaseIterable, Codable {
        case todo = "to_do"
        case progress = "in_progress"
        case done = "done"
    }
}
