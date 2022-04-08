//
//  BoardData.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

struct BoardData: Codable, Identifiable
{
    let id: String
    let boardName: String
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: BoardDataJsonKeys.self)
        
        guard let objectId = try? container.decode(String.self, forKey: .objectId) else {
            throw BoardDataDecodedError.objectIdError
        }
        
        guard let boardName = try? container.decode(String.self, forKey: .boardName) else {
            throw BoardDataDecodedError.boardNameError
        }
        
        self.id = objectId
        self.boardName = boardName
    }
    
    enum BoardDataJsonKeys: String, CodingKey {
        case objectId = "objectId"
        case boardName = "boardName"
    }
    
    enum BoardDataDecodedError: String, Error {
        case objectIdError = "objectId 값 디코딩에 실패하였습니다."
        case boardNameError = "boardName 값 디코딩에 실패하였습니다."
    }
}
