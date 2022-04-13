//
//  CardAction.swift
//  TodoList
//
//  Created by Bibi on 2022/04/12.
//

import Foundation

enum CardAction {
    case add
    case remove
    case update
    case move
    
    var name: String {
        switch self {
        case .add:
            return "등록"
        case .remove:
            return "삭제"
        case .update:
            return "변경"
        case .move:
            return "이동"
        }
    }
}
