//
//  TodoTarget.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

enum TodoTarget: BaseTarget {
    case loadColumn
}

extension TodoTarget {
    var path: String {
        switch self {
        case .loadColumn:
            return "/signup"
        }
    }
    
    var parameter: [String:Any]? {
        switch self {
        case .loadColumn:
            return [
                "id":"jkhome",
                "password":"asdfasdf"
            ]
        }
    }
    
    var method: String {
        switch self {
        case .loadColumn:
            return "POST"
        }
    }
}
