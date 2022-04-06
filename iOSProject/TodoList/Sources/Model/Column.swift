//
//  Column.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

struct Column: Codable {
    let title: String?
    let cards: [Card]
}
