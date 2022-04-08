//
//  TaskData.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/07.
//

import Foundation

class TaskData {
    var emoji: String
    var user: String
    var content: String
    var time: String
    
    init(emoji: String, user: String, content: String, time: String) {
        self.emoji = emoji
        self.user = user
        self.content = content
        self.time = time
    }
    
    static var dataList = [TaskData(emoji: "🥳", user: "@Jee", content: "해야할일에 블로그에 포스팅할 것을 등록하였습니다.", time: "1분 전"),
                           TaskData(emoji: "🥰", user: "@Jee", content: "해야할일에 블로그에 포스팅할 것을 등록하였습니다.", time: "1분 전"),
                           TaskData(emoji: "😇", user: "@Jee", content: "해야할일에 블로그에 포스팅할 것을 등록하였습니다.", time: "1분 전"),
                           TaskData(emoji: "🥲", user: "@Jee", content: "해야할일에 블로그에 포스팅할 것을 등록하였습니다.", time: "1분 전"),
                           TaskData(emoji: "😝", user: "@Jee", content: "해야할일에 블로그에 포스팅할 것을 등록하였습니다.", time: "1분 전")]
}
