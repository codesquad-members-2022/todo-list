//
//  NetworkError.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/06.
//


enum NetworkError:Error {
    case invalidURL
    case transportError(Error)
    case serverError(statusCode:Int)
    case noData
    case encodingError
    case decodingError
    case otherError(Error)
}
