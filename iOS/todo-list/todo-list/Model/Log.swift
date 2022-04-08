//
//  Log.swift
//  todo-list
//
//  Created by Jason on 2022/04/07.
//

import Foundation
import UIKit

class Log {
    
    private(set) var userName:String
    private(set) var title:String    // Task()에서 받아와서 보여줄 수정사항
    
    init(userName: String, title: String) {
        self.userName = userName
        self.title = title
    }
    
    //MARK: Title Method
    
    //MARK: Activity Method
    func showActivity() -> String {
        let tempData = Activate.Move
        
        if tempData != nil {
            
            switch tempData {
            case Activate.Move:
                return "이동"
            case Activate.Add:
                return "등록"
            case Activate.Delete:
                return "삭제"
            case Activate.Update:
                return "변경"
            }
        }
        return "잘못된 내용입니다."
    }
    
    //MARK: TimeStamp Method
    func showTimeStamp() -> Int {
        var count:Int = 0
        let react = showActivity()
        
        if react != nil {
            count += 1
        }
        
        return count
    }
    
    //MARK: TaskStatus(From-To) Method
    func showTaskStatus() -> Array<String> {
        let tempStatus = [TaskStatus.todo, TaskStatus.inProgress]
        var dumyStorage = [String]()
        
        // 입력된 부분이 From(Ex. 해야할 일) 과 To(Ex. 하고있는일)이 들어오면 처리
        if tempStatus != nil {
            for index in 0..<tempStatus.count {
                switch tempStatus[index] {
                case TaskStatus.todo:
                    dumyStorage.append("해야할 일")
                case TaskStatus.inProgress:
                    dumyStorage.append("하고있는 일")
                case TaskStatus.done:
                    dumyStorage.append("완료한 일")
                }
            }
        }
        return dumyStorage
    }
    
}
