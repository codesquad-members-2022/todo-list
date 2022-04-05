//
//  Card.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/05.
//

import Foundation

struct Card {
    private(set) var status: Status
    private(set) var title: String
    private(set) var contents: String
    private(set) var date: Date = Date.now
    private(set) var writer: String
    
    init(section: Status, title: String, contents: String, writer: String) {
        self.status = section
        self.title = title
        self.contents = contents
        self.writer = writer
    }
    
    enum Status {
        case todo
        case doing
        case done
    }
}

extension Card: CardUsable {
    func getSection() -> Status {
        return .todo
    }
    
    func getTitle() -> String {
        return title
    }
    
    func getContents() -> String {
        return contents
    }
    
    func getDate() -> Date {
        return date
    }
    
    func getWriter() -> String {
        return writer
    }
}

extension Card: CustomStringConvertible {
    var description: String {
        return "status : \(status), title : \(title), contents : \(contents), writer : \(writer), date: \(date)"
    }
}
