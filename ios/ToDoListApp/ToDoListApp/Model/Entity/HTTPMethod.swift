//
//  HTTPMethod.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/12.
//

import Foundation

enum HTTPMethod: String, CustomStringConvertible {
    case get
    case post
    case delete
    case patch
    
    var description: String {
        return self.rawValue.uppercased()
    }
}
