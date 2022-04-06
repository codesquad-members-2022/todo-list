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
            return "/loadColumn"
        }
    }
    
    var parameter: [String:Any]? {
        switch self {
        case .loadColumn: return nil
        }
    }
    
    var method: String {
        switch self {
        case .loadColumn:
            return "POST"
        }
    }
}
