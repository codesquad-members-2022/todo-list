//
//  Card.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/05.
//

import Foundation

struct Card: Codable {
    private(set) var status: Status
    private(set) var title: String
    private(set) var contents: String
    private(set) var date: Date
    private(set) var writer: String
    
    init(status: Status, title: String, contents: String, writer: String) {
        self.status = status
        self.title = title
        self.contents = contents
        self.writer = writer
        self.date = Date.now
    }
    
    enum Status: Codable {
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
