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
        }
    }
    
    var parameter: [String:Any]? {
        switch self {
        case .loadColumn: return nil
        case .moveCard(let cardIndex, let toColumn):
            return ["cardIndex": cardIndex, "toColumn": toColumn.rawValue]
        case .deleteCard(let cardIndex):
            return ["cardIndex": cardIndex]
        }
    }
    
    var method: String {
        switch self {
        case .loadColumn:
            return "GET"
        case .moveCard, .deleteCard:
            return "POST"
        }
    }
}
