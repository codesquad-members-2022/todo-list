//
//  EndPoint.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/07.
//

import Foundation

protocol Endpointable {
    func getHttpMethod() -> HTTPMethod
    func getBaseURL() -> BaseAddress
    func getPath() -> optionPath
    func getHeaders() -> [String:Any]?
    func getBody() -> [String:Any]?
}

extension Endpointable {
    func getURL() -> String { return getBaseURL().rawValue + getPath().pathString}
}


struct Endpoint:Endpointable {
    private let httpMethod: HTTPMethod
    private let baseURL: BaseAddress
    private let path: optionPath
    private let headers: [String: Any]?
    private let body: [String:Any]?
    
    init(httpMethod:HTTPMethod, baseURL:BaseAddress, path:optionPath, headers:[String:Any]?, body:[String:Any]? = nil) {
        self.httpMethod = httpMethod
        self.baseURL = baseURL
        self.path = path
        self.headers = headers
        self.body = body
    }
    
    func getHttpMethod() -> HTTPMethod {
        return self.httpMethod
    }
    
    func getBaseURL() -> BaseAddress {
        return self.baseURL
    }
    
    func getPath() -> optionPath {
        return self.path
    }
    
    func getHeaders() -> [String : Any]? {
        return self.headers
    }
    
    func getBody() -> [String:Any]? {
        return self.body
    }
}

enum EndPointCase {
    case getBoard
    case addCard(cardInfo: NewCard)
    case deleteCard(card:Todo)
    
    var endpoint:Endpointable {
        switch self {
        case .getBoard:
            return Endpoint(httpMethod: .get,
                            baseURL: .main,
                            path: .read,
                            headers: ["Content-Type": "application/json"],
                            body: nil
            )
            
        case .addCard(let cardInfo):
            return Endpoint(httpMethod: .post,
                            baseURL: .main,
                            path: .add,
                            headers: ["Content-Type": "application/json"],
                            body: cardInfo.body()
            )
        
        case .deleteCard(let card):
            return Endpoint(httpMethod: .delete,
                            baseURL: .main,
                            path: .delete(id: "\(card.id)"),
                            headers: ["Content-Type": "application/json"],
                            body: nil
            )
        }
    }
}

enum BaseAddress : String {
    case main = "http://13.125.161.84:8082/"
}

enum optionPath {
    case read
    case add
    case delete(id: String)
    
    var pathString: String {
        switch self {
        case .read:
            return "api/read/cards"
        case .add:
            return "api/cards/write"
        case .delete(let id):
            return "api/cards/\(id)"
        }
    }
}
