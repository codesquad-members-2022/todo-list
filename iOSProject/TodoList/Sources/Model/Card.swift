//
//  Card.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

struct Card: Decodable {
    let title: String
    let body: String?
    let caption: String?
}
