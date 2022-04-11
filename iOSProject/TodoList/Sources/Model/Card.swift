//
//  Card.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

struct Card: Codable, Equatable {
    let id: Int
    let title: String
    let content: String
    let caption: String
}
