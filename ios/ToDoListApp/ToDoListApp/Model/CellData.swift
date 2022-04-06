//
//  CellData.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/05.
//

import Foundation

class CellData {
    var title: String
    var content: String
    
    init(title: String, content: String) {
        self.title = title
        self.content = content
    }
    
    static var dataList = [CellData(title: "Team 07-member 1", content: "Mase"),
                           CellData(title: "Team 07-member 2", content: "Jee"),
                           CellData(title: "Team 07-member 3", content: "제리"),
                           CellData(title: "Team 07-member 4", content: "선을로")]
}
