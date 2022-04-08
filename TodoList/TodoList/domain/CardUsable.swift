//
//  CardUsable.swift
//  TodoList
//
//  Created by Bibi on 2022/04/05.
//

import Foundation

protocol CardUsable: TableCardUsable {
    func getDate() -> Date
}

protocol TableCardUsable {
    func getStatus() -> CardStatus
    func getTitle() -> String
    func getContents() -> String
    func getWriter() -> String
}
