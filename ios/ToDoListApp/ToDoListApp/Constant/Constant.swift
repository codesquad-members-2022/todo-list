//
//  Constant.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/08.
//

import Foundation

enum Constant {
    
    enum TableViewCellIdentifier {
        static let kanban = "KanbanTableViewCell"
        static let inspector = "InspectorTableViewCell"
    }
    
    enum Text {
        static let mainViewControllerTitle = "TO-DO LIST"
        static let authorByIOS = "author by iOS"
    }
    
    enum Font {
        static let gothicNeo = "Apple SD Gothic Neo"
        static let gothicNeoBold = "Apple SD Gothic Neo Bold"
    }
    
    enum SFSymbol {
        static let lineThreeHorizontal = "line.3.horizontal"
        static let xmark = "xmark"
        static let plus = "plus"
    }
    
    enum KanbanTitle {
        static let toDo = "해야 할 일"
        static let inProgress = "하고 있는 일"
        static let done = "완료한 일"
    }
}
