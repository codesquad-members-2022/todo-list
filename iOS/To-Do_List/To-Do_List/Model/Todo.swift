//
//  NetworkModel.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/06.
//

import Foundation

protocol CardDisplayable {
    var id: Int? { get }
    var title: String { get }
    var content: String { get }
}

struct Todo: CardDisplayable, Codable, Equatable {
    let id:Int?
    let title:String
    let writer:String
    let content:String
    let createdAt:String
}
