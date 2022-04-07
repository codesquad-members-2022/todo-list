//
//  KanbanType.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/07.
//

import Foundation

enum KanbanType: String {
    case toDo
    case inProgress
    case done
    
    var title: String {
        switch self {
        case .toDo:
            return "해야 할 일"
        case .inProgress:
            return "하고 있는 일"
        case .done:
            return "완료한 일"
        }
    }
}
