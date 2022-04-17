//
//  KanbanType.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/07.
//

import Foundation

enum KanbanType: CaseIterable, CustomStringConvertible {
    
    case toDo
    case inProgress
    case done
    
    var description: String {
        switch self {
        case .toDo:
            return Constant.KanbanTitle.toDo
        case .inProgress:
            return Constant.KanbanTitle.inProgress
        case .done:
            return Constant.KanbanTitle.done
        }
    }
}
