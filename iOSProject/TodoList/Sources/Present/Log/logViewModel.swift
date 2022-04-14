//
//  logViewModel.swift
//  TodoList
//
//  Created by Joobang Lee on 2022/04/14.
//

import Foundation

class LogViewModel{
    func loadData() -> [ActivityLog]{
        var logArr = [ActivityLog]()
        for _ in 0...2{
            let activityLog = ActivityLog.init(author: "shingha", createdDate: "1일", text: "가나다라마바")
            logArr.append(activityLog)
        }
        return logArr
    }
    
}
