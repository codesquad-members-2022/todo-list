//
//  NetworkModel.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/06.
//

import Foundation

struct Todo:Codable {
    let memberLoginId:String
    let id:Int
    let createdAt:String
    let title:String
    let content:String
}

typealias Todolist = [Todo]

