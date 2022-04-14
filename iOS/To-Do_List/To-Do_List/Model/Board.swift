//
//  Board.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/14.
//

import Foundation

struct Board:Codable {
    let todoItems:[Todo]?
    let progressingItems:[Todo]?
    let completedItems:[Todo]?
}

enum BoardType: CustomStringConvertible, Codable {
    
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
    
    var type:String {
        switch self {
        case .todo:
            return "TODO"
        case .progressing:
            return "PROGRESSING"
        case .completed:
            return "COMPLETED"
        }
    }
    
    
}

extension BoardType {
    
    func extractList(from data: NetworkResult) -> [Todo]? {
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
