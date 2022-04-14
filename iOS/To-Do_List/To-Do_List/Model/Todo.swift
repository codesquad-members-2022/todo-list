//
//  NetworkModel.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/06.
//

import Foundation

protocol CardDisplayable {
    var id:Int? { get }
    var title:String { get }
    var content:String { get }
}

struct Todo:CardDisplayable, Codable,Equatable {
    let id:Int?
    let title:String
    let writer:String?
    let position:Int?
    let content:String
    let createdAt:String?
    let cardType:String?
    let memberId:Int?
}

//"id": 122,
//     "title": "수정한내용",
//     "writer": null,
//     "position": null,
//     "content": "제발",
//     "createdAt": null,
//     "cardType": null,
//     "memberId": null
