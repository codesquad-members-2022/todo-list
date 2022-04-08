//
//  TodoTarget.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

enum TodoTarget: BaseTarget {
    case loadColumn(_ column: Card.Column)
    case moveCard(_ cardId: Int, fromColumn: Card.Column, toColumn: Card.Column)
    case deleteCard(_ cardId: Int)
    case editCard(_ cardId: Int, title: String, body: String)
    case addCard(title: String, body: String, column: Card.Column)
}

extension TodoTarget {
    var path: String {
        switch self {
        case .loadColumn:
            return "/loadColumn"
        case .moveCard:
            return "/moveCard"
        case .deleteCard:
            return "/deleteCard"
        case .editCard:
            return "/editCard"
        case .addCard:
            return "/addCard"
        }
    }
    
    var parameter: [String:Any]? {
        switch self {
        case .loadColumn(let column):
            return ["column": column.rawValue]
        case .addCard(let title, let body, let column):
            return ["title": title, "content":body, "column":column.rawValue]
        case .moveCard(let cardId, let fromColumn, let toColumn):
            return ["id": cardId, "from_column": fromColumn.rawValue, "to_column": toColumn.rawValue]
        case .editCard(let cardId, let title, let body):
            return ["id": cardId, "title": title, "content":body]
        case .deleteCard(let cardId):
            return ["id": cardId]
        }
    }
    
    var method: String {
        switch self {
        case .loadColumn:
            return "GET"
        case .moveCard, .deleteCard, .editCard, .addCard:
            return "POST"
        }
    }
}
