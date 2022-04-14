//
//  LogViewModel.swift
//  todo-list
//
//  Created by Jason on 2022/04/12.
//

import Foundation
import UIKit

// Title: ViewController에게 LogViewModel이 뭘 넘겨야할까??
// -> Action에 영향에 따른 각각의 다른 String 출력 내용들!
class LogViewModel {
    
    let logManager = LogManager()
    
    var descriptions: [String] = []
    var userIds: [String] = []
    
    func load() {
        logManager.load { logs in
            for log in logs {
                self.descriptions.append(self.createDescription(log))
                self.userIds.append(self.createUserName(log))
            }
        }
    }
    
    // 필요한 요소 = TaskTitle, From-state, To-status
    func createDescription(_ log: Log) -> String {
        
        switch log.action {
            
        case .Add:
            guard let to = log.to else { return "" }
            return "\(log.taskTitle)을 \(to)에 추가하였습니다."
            
        case .Update:
            guard let to = log.to else { return "" }
            guard let from = log.from else { return "" }
            
            return "\(log.taskTitle)을 \(from)에서 \(to)로 변경하였습니다."
            
        case .Delete:
            guard let from = log.from else { return "" }
            return "\(log.taskTitle)을 \(from)에서 삭제하였습니다."
            
        case .Move:
            guard let to = log.to else { return "" }
            guard let from = log.from else { return "" }
            
            if log.from == log.to {
                return "\(log.taskTitle)의 내용을 변경하였습니다."
            } else {
                return "\(log.taskTitle)을 \(from)에서 \(to)로 이동하였습니다."
            }
        }
    }
    
    //(UserName) @{UserID}
    func createUserName(_ log: Log) -> String {
        return log.taskTitle
    }
    
}

struct logCellViewModel {
    let id: Int
    let title: String
    let content: String
    let timeLog: String
}
