//
//  Card.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/05.
//

import Foundation

struct Card: Codable {
    private(set) var status: CardStatus
    private(set) var title: String
    private(set) var contents: String
    private(set) var date: Date
    private(set) var writer: String
    
    init(status: CardStatus, title: String, contents: String, writer: String) {
        self.status = status
        self.title = title
        self.contents = contents
        self.writer = writer
        self.date = Date.now
    }
}

extension Card: CardUsable {
    func getStatus() -> CardStatus {
        return status
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
