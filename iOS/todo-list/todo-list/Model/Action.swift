//
//  Activity.swift
//  todo-list
//
//  Created by Jason on 2022/04/07.
//

import Foundation

enum Action: String, Decodable {
    case Move = "MOVE"
    case Add = "ADD"
    case Delete = "DELETE"
    case Update = "UPDATE"
}
