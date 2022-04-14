//
//  EndPoint.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/07.
//

import Foundation

protocol Endpointable {
    func getHttpMethod() -> HTTPMethod
    func getBaseURL() -> BaseURL
    func getPath() -> Path
    func getHeaders() -> [String:Any]?
    func getBody() -> [String:Any]?
}

extension Endpointable {
    func getURL() -> String { return getBaseURL().urlString + getPath().pathString }
}


struct Endpoint:Endpointable {
    private let httpMethod: HTTPMethod
    private let baseURL: BaseURL
    private let path: Path
    private let headers: [String: Any]?
    private let body: [String:Any]?
    
    init(httpMethod:HTTPMethod, baseURL:BaseURL, path:Path, headers:[String:Any]?, body:[String:Any]? = nil) {
        self.httpMethod = httpMethod
        self.baseURL = baseURL
        self.path = path
        self.headers = headers
        self.body = body
    }
    
    func getHttpMethod() -> HTTPMethod {
        return self.httpMethod
    }
    
    func getBaseURL() -> BaseURL {
        return self.baseURL
    }
    
    func getPath() -> Path {
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
    case addCard(card: NewCard)
    case deleteCard(cardId:Int)
    case moveToCompleted(movedCard : MovedCard)
    case editCard(card: NewCard)
    
    var endpoint:Endpointable {
        switch self {
        case .getBoard:
            return Endpoint(httpMethod: .get,
                            baseURL: .main,
                            path: .get,
                            headers: ["Content-Type": "application/json"],
                            body: nil
            )
       
            
        case .addCard(let cardInfo):
            return Endpoint(httpMethod: .post,
                            baseURL: .main,
                            path: .post,
                            headers: ["Content-Type": "application/json"],
                            body: cardInfo.body()
            )
        
        case .deleteCard(let cardId):
            return Endpoint(httpMethod: .delete,
                            baseURL: .main,
                            path: .delete(cardId: cardId),
                            headers: ["Content-Type": "application/json"],
                            body: nil
            )
            
        case .moveToCompleted(let movedCard):
            return Endpoint(httpMethod: .patch,
                            baseURL: .main,
                            path: .edit(cardId: movedCard.id),
                            headers: ["Content-Type": "application/json"],
                            body: movedCard.body()
            )
            
        case .editCard(let cardInfo):
            return Endpoint(httpMethod: .patch,
                            baseURL: .main,
                            path: .edit(cardId: cardInfo.id ?? 0),
                            headers: ["Content-Type": "application/json"],
                            body: cardInfo.body()
            )
        }
    }
}

enum BaseURL {
    case main
    
    var urlString:String {
        switch self {
        case .main:
            return "http://13.125.161.84:8082/"
        }
    }
}

enum Path {
    case get
    case post
    case delete(cardId:Int)
    case edit(cardId:Int)
    
    var pathString:String {
        switch self {
        case .get:
            return "api/cards"
        case .post:
            return "api/cards"
        case .delete(let id):
            return "api/cards/\(id)"
        case .edit(let id):
            return "api/cards/\(id)"
        }
    }
    
}
