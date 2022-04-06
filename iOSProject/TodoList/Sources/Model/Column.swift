//
//  Column.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

struct Column: Decodable {
    let title: String
    let cards: [Card]
}
