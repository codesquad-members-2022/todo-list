//
//  Card.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/05.
//

import Foundation

struct Card{
    private(set) var section: Section
    private(set) var title: String
    private(set) var contents: String
    private(set) var date: Date = Date.now
    private(set) var writer: String
    
    init(section: Section, title: String, contents: String, writer: String){
        self.section = section
        self.title = title
        self.contents = contents
        self.writer = writer
    }
    
    enum Section{
        case todo
        case doing
        case done
    }
}
extension Card: CardUsable{
    func getSection() -> Section {
        return .todo
    }
    
    func getTitle() -> String{
        return title
    }
    
    func getContents() -> String{
        return contents
    }
    
    func getDate() -> Date{
        return date
    }
    
    func getWriter() -> String{
        return writer
    }
}

protocol CardUsable{
    func getSection() -> Card.Section
    func getTitle() -> String
    func getContents() -> String
    func getDate() -> Date
    func getWriter() -> String
}
