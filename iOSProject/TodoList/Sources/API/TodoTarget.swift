//
//  TodoTarget.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

enum TodoTarget: BaseTarget {
    case loadColumn
    case moveCard(_ cardIndex: Int, toColumn: Card.Status)
    case deleteCard(_ cardIndex: Int)
    case editCard(_ cardIndex: Int, title: String, body: String)
    case addCard(title: String, body: String)
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
        case .loadColumn: return nil
        case .moveCard(let cardIndex, let toColumn):
            return ["cardIndex": cardIndex, "toColumn": toColumn.rawValue]
        case .deleteCard(let cardIndex):
            return ["cardIndex": cardIndex]
        case .editCard(let cardIndex, let title, let body):
            return ["cardIndex": cardIndex, "title": title, "body":body]
        case .addCard(let title, let body):
            return ["title": title, "body":body]
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
