//
//  NetworkError.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/13.
//

import Foundation

enum NetworkError: Error, CustomStringConvertible {
    
    case invalidURL
    case invalidStatusCode(code: Int)
    case invalidRequest
    case invalidResponse
    case emptyData
    case encodingError
    case decodingError
    case error
    
    var description: String {
        switch self {
        case .invalidURL:
            return "Invalid URL"
        case .invalidStatusCode(let code):
            return "Invalid Status Code : \(code)"
        case .invalidRequest:
            return "Invalid Request"
        case .invalidResponse:
            return "Invaild Response"
        case .emptyData:
            return "Empty Data"
        case .encodingError:
            return "Encoding Error"
        case .decodingError:
            return "Decoding Error"
        case .error:
            return "error"
        }
    }
    
}
