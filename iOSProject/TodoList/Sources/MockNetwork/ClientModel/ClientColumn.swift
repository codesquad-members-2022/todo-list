//
//  ClientColumn.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/09.
//

import Foundation

struct ClientColumn: Encodable {
    let type: MockColumnType
    let cards: [ClientCard]
}
