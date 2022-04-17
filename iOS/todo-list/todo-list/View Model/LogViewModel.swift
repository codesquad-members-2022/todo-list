//
//  LogViewModel.swift
//  todo-list
//
//  Created by Jason on 2022/04/12.
//

import Foundation
import UIKit

protocol LogViewModelDelegate {
    func update()
}

class LogViewModel {
    
    var logManager = LogManager()
    var descriptions: [String] = []
    var userIds: [String] = []
    
    var delegate: LogViewModelDelegate?
    
    init(logManager: LogManager) {
        self.logManager = logManager
    }
    
    var cellViewModels = [LogCellViewModel]()
    var count: Int { cellViewModels.count }
    
    func get(index: Int) -> LogCellViewModel {
        return cellViewModels[index]
    }

}

//MARK: - Load to Domain Data
extension LogViewModel {
    
    func load() {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        dateFormatter.timeZone = TimeZone(abbreviation: "KST")
        dateFormatter.locale = Locale(identifier: "ko_kr")
        
        logManager.load { logs in
            for log in logs {
                let cellViewModel = LogCellViewModel(userName: log.userId, emoji: UIImage(named: "Party Face Emoji")!, description: self.createDescription(log), timeLog: dateFormatter.string(from: log.createdAt))
                self.cellViewModels.append(cellViewModel)
            }
        }
        delegate?.update()
    }
    
    // 필요한 요소 = TaskTitle, From-state, To-status
    func createDescription(_ log: Log) -> String {
        
        switch log.action {
            
        case .Add:
            guard let to = log.toStatus else { return "" }
            return "\(log.taskTitle)을 \(to)에 추가하였습니다."
            
        case .Update:
            guard let to = log.toStatus else { return "" }
            guard let from = log.fromStatus else { return "" }
            
            return "\(log.taskTitle)을 \(from)에서 \(to)로 변경하였습니다."
            
        case .Delete:
            guard let from = log.fromStatus else { return "" }
            return "\(log.taskTitle)을 \(from)에서 삭제하였습니다."
            
        case .Move:
            guard let to = log.toStatus else { return "" }
            guard let from = log.fromStatus else { return "" }

            if log.fromStatus == log.toStatus {
                return "\(log.taskTitle)의 내용을 변경하였습니다."
            } else {
                return "\(log.taskTitle)을 \(from)에서 \(to)로 이동하였습니다."
            }
        }
    }
}

struct LogCellViewModel {
    let userName: String
    let emoji : UIImage
    let description: String
    let timeLog: String
}
