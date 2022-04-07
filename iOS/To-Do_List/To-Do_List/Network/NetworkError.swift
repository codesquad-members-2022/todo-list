//
//  NetworkError.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/06.
//


enum NetworkError:Error {
    case responseError
    case encodingError
    case decodingError
    case otherError
}
