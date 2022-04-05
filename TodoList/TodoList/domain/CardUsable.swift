//
//  CardUsable.swift
//  TodoList
//
//  Created by Bibi on 2022/04/05.
//

import Foundation

protocol CardUsable {
    func getSection() -> Card.Status
    func getTitle() -> String
    func getContents() -> String
    func getDate() -> Date
    func getWriter() -> String
}
