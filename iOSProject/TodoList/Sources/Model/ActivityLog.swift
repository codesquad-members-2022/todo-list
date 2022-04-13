//
//  ActivityLog.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/13.
//

import Foundation

struct ActivityLog: Decodable {
    let author: String
    let createdDate: String
    let text: String
}
