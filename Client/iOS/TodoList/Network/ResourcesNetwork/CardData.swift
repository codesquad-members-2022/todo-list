//
//  CardData.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

struct CardData: Codable
{
    var objectId: String
    var title: String
    var contents: String
    var creationDate: String
    var index: Int
    var status: Int
    var updateDate: String
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CardDataJsonKeys.self)
        
        guard let objectId = try? container.decode(String.self, forKey: .objectId) else {
            throw CardDataDecodedError.objectIdError
        }
        
        guard let title = try? container.decode(String.self, forKey: .title) else {
            throw CardDataDecodedError.titleError
        }
        
        guard let contents = try? container.decode(String.self, forKey: .contents) else {
            throw CardDataDecodedError.contentsError
        }
        
        guard let creationDate = try? container.decode(String.self, forKey: .creationDate) else {
            throw CardDataDecodedError.creationDateError
        }
        
        guard let index = try? container.decode(Int.self, forKey: .index) else {
            throw CardDataDecodedError.indexError
        }
        
        guard let status = try? container.decode(Int.self, forKey: .status) else {
            throw CardDataDecodedError.statusError
        }
        
        guard let updateDate = try? container.decode(String.self, forKey: .updateDate) else {
            throw CardDataDecodedError.updateDateError
        }
        
        self.objectId = objectId
        self.title = title
        self.contents = contents
        self.creationDate = creationDate
        self.index = index
        self.status = status
        self.updateDate = updateDate
    }
    
    enum CardDataJsonKeys: String, CodingKey {
        case objectId = "objectId"
        case title = "title"
        case contents = "contents"
        case creationDate = "creationDate"
        case index = "index"
        case status = "status"
        case updateDate = "updateDate"
    }
    
    enum CardDataDecodedError: String, Error {
        case objectIdError = "objectId 값 디코딩에 실패하였습니다."
        case titleError = "title 값 디코딩에 실패하였습니다."
        case contentsError = "contents 값 디코딩에 실패하였습니다."
        case creationDateError = "creationDate 값 디코딩에 실패하였습니다."
        case indexError = "index 값 디코딩에 실패하였습니다."
        case statusError = "status 값 디코딩에 실패하였습니다."
        case updateDateError = "updateDate 값 디코딩에 실패하였습니다."
    }
}
