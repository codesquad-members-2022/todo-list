//
//  CardData.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

struct CardData: Codable, Identifiable
{
    let id: Int // Identifiable required property
    let title: String
    let contents: String
    let boardName: String
    let creationDate: String
    let index: Int
    let status: Int
    let updateDate: String
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CardDataJsonKeys.self)
        
        guard let objectId = try? container.decode(Int.self, forKey: .cardId) else {
            throw CardDataDecodedError.objectIdError
        }
        
        guard let title = try? container.decode(String.self, forKey: .cardTitle) else {
            throw CardDataDecodedError.titleError
        }
        
        guard let contents = try? container.decode(String.self, forKey: .cardContent) else {
            throw CardDataDecodedError.contentsError
        }
        
        guard let boardName = try? container.decode(String.self, forKey: .boardName) else {
            throw CardDataDecodedError.boardNameError
        }
        
        self.id = objectId
        self.title = title
        self.contents = contents
        self.boardName = boardName
        self.creationDate = (try? container.decode(String.self, forKey: .creationDate)) ?? ""
        self.index = (try? container.decode(Int.self, forKey: .index)) ?? 0
        self.status = (try? container.decode(Int.self, forKey: .status)) ?? 0
        self.updateDate = (try? container.decode(String.self, forKey: .updateDate)) ?? ""
    }
    
    func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CardDataJsonKeys.self)
        try container.encode(id, forKey: .cardId)
        try container.encode(title, forKey: .cardTitle)
        try container.encode(contents, forKey: .cardContent)
        try container.encode(creationDate, forKey: .creationDate)
        try container.encode(index, forKey: .index)
        try container.encode(status, forKey: .status)
        try container.encode(updateDate, forKey: .updateDate)
    }
    
    enum CardDataJsonKeys: String, CodingKey {
        case cardId = "cardId"
        case cardTitle = "cardTitle"
        case cardContent = "cardContent"
        case boardName = "boardName"
        case creationDate = "creationDate"
        case index = "index"
        case status = "status"
        case updateDate = "updateDate"
    }
    
    enum CardDataDecodedError: String, Error {
        case objectIdError = "cardId 값 디코딩에 실패하였습니다."
        case titleError = "title 값 디코딩에 실패하였습니다."
        case contentsError = "contents 값 디코딩에 실패하였습니다."
        case creationDateError = "creationDate 값 디코딩에 실패하였습니다."
        case indexError = "index 값 디코딩에 실패하였습니다."
        case statusError = "status 값 디코딩에 실패하였습니다."
        case updateDateError = "updateDate 값 디코딩에 실패하였습니다."
        case boardNameError = "boardNameError 값 디코딩에 실패하였습니다."
    }
}
