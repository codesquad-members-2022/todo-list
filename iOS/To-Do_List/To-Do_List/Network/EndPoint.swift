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
    func getBody() -> [String:Any]?
}

extension Endpointable {
    func getURL() -> String { return getBaseURL() + getPath() }
}


struct Endpoint:Endpointable {
    private let httpMethod: HTTPMethod
    private let baseURL: String
    private let path: String
    private let headers: [String: Any]?
    private let body: [String:Any]?
    
    init(httpMethod:HTTPMethod, baseURL:String, path:String, headers:[String:Any]?, body:[String:Any]? = nil) {
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
    
    func getBody() -> [String:Any]? {
        return self.body
    }
}

enum EndPointCase {
    case getBoard
    case addCard(card: CardInfo)
    case deleteCard(card:Todo)
    
    var endpoint:Endpointable {
        switch self {
        case .getBoard:
            return Endpoint(httpMethod: .get,
                            baseURL: "http://13.125.161.84:8082/",
                            path: "api/read/cards",
                            headers: ["Content-Type": "application/json"],
                            body: nil
            )
            
        case .addCard(let cardInfo):
            return Endpoint(httpMethod: .post,
                            baseURL: "http://13.125.161.84:8082/",
                            path: "api/cards/write",
                            headers: ["Content-Type": "application/json"],
                            body: cardInfo.body()
            )
        
        case .deleteCard(let card):
            return Endpoint(httpMethod: .delete,
                            baseURL: "http://13.125.161.84:8082/",
                            path: "api/cards/\(card.id)",
                            headers: ["Content-Type": "application/json"],
                            body: nil
            )
        }
    }
}
