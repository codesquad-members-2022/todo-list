//
//  EndPoint.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/07.
//

import Foundation

protocol Endpointable {
    func getHttpMethod() -> HTTPMethod
    func getBaseURL() -> String
    func getPath() -> String
    func getHeaders() -> [String:Any]?
    func getBody() -> Encodable?
}

extension Endpointable {
    func getURL() -> String { return getBaseURL() + getPath() }
}


struct Endpoint:Endpointable {
    private let httpMethod: HTTPMethod
    private let baseURL: String
    private let path: String
    private let headers: [String: Any]?
    private let body: Encodable?
    
    init(httpMethod:HTTPMethod, baseURL:String, path:String, headers:[String:Any]?, body:Encodable? = nil) {
        self.httpMethod = httpMethod
        self.baseURL = baseURL
        self.path = path
        self.headers = headers
        self.body = body
    }
    
    func getHttpMethod() -> HTTPMethod {
        return self.httpMethod
    }
    
    func getBaseURL() -> String {
        return self.baseURL
    }
    
    func getPath() -> String {
        return self.path
    }
    
    func getHeaders() -> [String : Any]? {
        return self.headers
    }
    
    func getBody() -> Encodable? {
        return self.body
    }
}

enum EndPointCase {
    case getBoard
    case postBoard(board:Board)
    
    var endpoint:Endpointable {
        switch self {
        case .getBoard:
            return Endpoint(httpMethod: .get,
                            baseURL: "http://13.125.161.84:8082/",
                            path: "api/read/cards",
                            headers: ["Content-Type": "application/json"],
                            body: nil
            )
            
        case .postBoard(let board):
            return Endpoint(httpMethod: .post,
                            baseURL: "http://13.125.161.84:8082/",
                            path: "api/read/cards",
                            headers: ["Content-Type": "application/json"],
                            body: board
            )
        }
    }
}
