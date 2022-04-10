//
//  KanbanTableCellData.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/05.
//

import Foundation

class KanbanTableCellData {
    var title: String
    var content: String
    
    init(title: String, content: String) {
        self.title = title
        self.content = content
    }
    
    static var dataList = [KanbanTableCellData(title: "GitHub 공부하기", content: "* add \n* commit \n* push \n* 코드스쿼드 짱"),
                           KanbanTableCellData(title: "블로그에 포스팅할 것", content: "* GitHub 공부내용 \n* 모던 자바스크립트 1장 공부 내용 \n* Todo List 만들기"),
                           KanbanTableCellData(title: "백엔드", content: "제리 \n선을로"),
                           KanbanTableCellData(title: "iOS", content: "메이스, 지")]
}
