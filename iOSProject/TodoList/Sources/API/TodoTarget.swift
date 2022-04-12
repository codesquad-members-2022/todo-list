//
//  TodoTarget.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

enum TodoTarget: BaseTarget {
    case loadColumns
    case moveCard(_ cardId: Int, toColumn: Column.ColumnType, toIndex: Int)
    case deleteCard(_ cardId: Int)
    case editCard(_ cardId: Int, title: String, body: String)
    case addCard(title: String, body: String, column: Column.ColumnType, authorSystem: String)
}

extension TodoTarget {
    var path: String {
        switch self {
        case .loadColumns, .addCard, .moveCard:
            return "/cards"
        case .deleteCard(let cardId):
            return "/cards/\(cardId)"
        case .editCard(let cardId, _, _):
            return "/cards/\(cardId)"
        }
    }
    
    var parameter: [String:Any]? {
        switch self {
        case .loadColumns:
            return nil
        case .addCard(let title, let body, let column, let authorSystem):
            return ["title": title, "content":body, "columnName":column.rawValue, "authorSystem": authorSystem]
        case .moveCard(let cardId, let toColumn, let index):
            return ["id": cardId, "column": toColumn.rawValue, "index": index]
        case .editCard( _, let title, let body):
            return ["title": title, "content":body]
        case .deleteCard:
            return nil
        }
    }
    
    var method: String {
        switch self {
        case .loadColumns:
            return "GET"
        case .moveCard, .editCard:
            return "PUT"
        case .addCard:
            return "POST"
        case .deleteCard:
            return "DELETE"
        }
    }
}
