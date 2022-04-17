//
//  NotificationCenter.swift
//  TodoApp
//
//  Created by YEONGJIN JANG on 2022/04/13.
//

import Foundation

extension Notification.Name {
    static let successfulFetch = Notification.Name("success fetch data from server")
    static let failedFetch = Notification.Name("fail fetch data from server")
}

enum NotificationKey {
    case activity
    case card
    case column
}
