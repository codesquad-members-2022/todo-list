//
//  JsonConverter.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/12.
//

import Foundation

final class JsonConverter{
    static func decodeJson<T: Codable>(data: Data) -> [T]?{
        do{
            let result = try JSONDecoder().decode([T].self, from: data)
            return result
        } catch{
            return nil
        }
    }
    
    static func encodeJson<T: Codable>(param: T) -> Data?{
        do{
            let result = try JSONEncoder().encode(param)
            return result
        } catch{
            return nil
        }
    }
}
