//
//  PostResponse.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/14.
//

import Foundation

struct PostResponse: Codable{
    private(set) var id: Int
    private(set) var modifiedAt: String
}
