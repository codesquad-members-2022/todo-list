//
//  CardStatus.swift
//  TodoList
//
//  Created by Bibi on 2022/04/07.
//

import Foundation

enum CardStatus: Codable {
    case todo
    case doing
    case done

    var name: String {
        switch self {
        case .todo:
            return "해야 할 일"
        case .doing:
            return "하고 있는 일"
        case .done:
            return "완료한 일"
        }
    }
}
