//
//  ClientColumn.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/09.
//

import Foundation

struct ClientColumn: Encodable {
    let columnType: MockColumnType
    let cards: [ClientCard]
}

struct ClientColumns: Encodable {
    let columns: [ClientColumn]
}
