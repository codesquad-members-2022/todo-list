//
//  APIError.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation

enum APIError: LocalizedError {
    case unknown
    case httpStatusError(Int, String)
}

//struct APIError: LocalizedError, Decodable {
//
//    let statusCode: Int
//
//    enum CodingKeys: String, CodingKey {
//        case statusCode = "status"
//    }
//
//    static let defaultValue: APIError = {
//        .init(statusCode: -9999)
//    }()
//}
