//
//  constants.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/07.
//

import Foundation

enum BoardType: CustomStringConvertible {
    
    case todo
    case progressing
    case completed
    
    var description: String {
        switch self {
        case .todo:
            return "해야할 일"
        case .progressing:
            return "하고 있는 일"
        case .completed:
            return "완료한 일"
        }
    }
    
}

extension BoardType {
    
    func selectData(data: NetworkResult) -> [Todo] {
        switch self {
        case .todo:
            return data.response.todoItems
        case .progressing:
            return data.response.progressingItems
        case .completed:
            return data.response.completedItems
        }
    }
    
}
