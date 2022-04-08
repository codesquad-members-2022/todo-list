//
//  HistoryData.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

struct HistoryData: Codable, Identifiable
{
    let id: String
    let activityType: String
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: HistoryDataJsonKeys.self)
        
        guard let objectId = try? container.decode(String.self, forKey: .objectId) else {
            throw HistoryDataDecodedError.objectIdError
        }
        
        guard let activityType = try? container.decode(String.self, forKey: .activityType) else {
            throw HistoryDataDecodedError.objectIdError
        }
        
        self.id = objectId
        self.activityType = activityType
    }
    
    enum HistoryDataJsonKeys: String, CodingKey {
        case objectId = "objectId"
        case activityType = "activityType"
    }
    
    enum HistoryDataDecodedError: String, Error {
        case objectIdError = "objectId 값 디코딩에 실패하였습니다."
        case activityTypeError = "activityType 값 디코딩에 실패하였습니다."
    }
}
