//
//  Configuration.swift
//  TodoApp Dev
//
//  Created by 송태환 on 2022/04/13.
//

import Foundation

enum Configuration {
    enum Error: Swift.Error {
        case invalidKey, invalidValue
    }
    
    static func value<T>(for key: String) throws -> T where T: LosslessStringConvertible {
        guard let object = Bundle.main.object(forInfoDictionaryKey: key) else {
            throw Error.invalidKey
        }
        
        switch object {
        case let value as T:
            return value
        case let string as String:
            guard let value = T.init(string) else { fallthrough }
            return value
        default:
            throw Error.invalidValue
        }
        
    }
}

enum API {
    static var baseURL: URL {
        return try! URL(string: Configuration.value(for: "SERVER_BASE_URL"))!
    }
}
