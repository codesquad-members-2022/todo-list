//
//  TodoTarget.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

enum TodoTarget: BaseTarget {
    case loadColumn
    case moveCard(cardIndex: Int, toColumn: Card.Status)
}

extension TodoTarget {
    var path: String {
        switch self {
        case .loadColumn:
            return "/loadColumn"
        case .moveCard:
            return "/moveCard"
        }
    }
    
    var parameter: [String:Any]? {
        switch self {
        case .loadColumn: return nil
        case .moveCard(let cardIndex, let toColumn):
            return ["cardIndex": cardIndex, "toColumn": toColumn.rawValue]
        }
    }
    
    var method: String {
        switch self {
        case .loadColumn:
            return "GET"
        case .moveCard:
            return "POST"
        }
    }
}
