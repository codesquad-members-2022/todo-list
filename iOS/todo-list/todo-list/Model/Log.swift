//
//  Log.swift
//  todo-list
//
//  Created by Jason on 2022/04/07.
//

import Foundation

class Log {
    
    private(set) var userName:String
    private(set) var title:String    // Task()에서 받아와서 보여줄 수정사항
    
    init(userName: String, title: String) {
        self.userName = userName
        self.title = title
    }
    
    //MARK: Activity Area
    func showActivity() -> Activate {
        let dumySituation:Array<String> = ["이동", "등록", "삭제", "변경"]
        
        switch dumySituation {
        case ["이동"]:
            return Activate.Move
        case ["등록"]:
            return Activate.Add
        case ["삭제"]:
            return Activate.Delete
        case ["변경"]:
            return Activate.Update
        default:
            return Activate.Move
        }
    }
    
    //MARK: TimeStamp Area
    func showTimeStamp() -> Int {
        var count:Int = 0
        let react = showActivity()
        
        if react != nil {
            count += 1
        }
        
        return count
    }
    
    //MARK: TaskStatus(From-To) Area
    func showTaskStatus() -> Array<String> {
        let dumyStatus:Array<String> = ["todo", "inProcess", "done"]
        var dumyStorage = [String]()
        
        // 입력된 부분이 From(Ex. 해야할 일) 과 To(Ex. 하고있는일)이 들어오면 처리
        
        return dumyStorage
    }
    
}
