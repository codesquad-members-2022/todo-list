//
//  SessionCommonAttributes.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

class SessionCommonAttributes {
    
    // MARK: - Local Properties
    private var request: URLRequest!
    
    var urlString: String
    
    var httpMethod: HTTPMethod = .POST
    {
        didSet {
            request.httpMethod = httpMethod.getValue()
        }
    }
    var mimeType: MIMEType = .applicationJSON
    {
        didSet {
            request.setValue(MIMEType.applicationJSON.rawValue, forHTTPHeaderField: httpHeaderField.rawValue)
        }
    }
    var httpHeaderField: ReservedHTTPHeader = .contentLength
    {
        didSet {
            request.setValue(MIMEType.applicationJSON.rawValue, forHTTPHeaderField: httpHeaderField.rawValue)
        }
    }
    
    private var cachePolicy = URLRequest.CachePolicy.useProtocolCachePolicy
    private var timeoutInterval: TimeInterval = 6.0
    
    // MARK: - Initializers
    init?(as string: String)
    {
        self.urlString = string
        if let url = URL(string: string) {
            reloadRequestURL(url)
        } else {
            return nil
        }
    }
    
    func getRequestHandler(url: URL, completionHandler: @escaping (URLRequest) -> Void) {
        reloadRequestURL(url)
        completionHandler(request)
    }
    
    func getCurrentRequestHandler(completionHandler: @escaping (URLRequest) -> Void) {
        completionHandler(request)
    }
    
    private func reloadRequestURL(_ url: URL) {
        request = URLRequest(url: url, cachePolicy: cachePolicy, timeoutInterval: timeoutInterval)
        request.httpMethod = httpMethod.rawValue
        request.setValue(mimeType.rawValue, forHTTPHeaderField: httpHeaderField.rawValue)
    }
    
    // MARK: - HTTPMethod Types
    
    enum HTTPMethod: String
    {
        case POST
        case GET
        
        func getValue() -> String {
            switch self {
            case .GET:
                return "GET"
            case .POST:
                return "POST"
            }
        }
    }
    
    // MARK: - MIMEType Types
    
    enum MIMEType: String
    {
        case applicationOctetStream = "application/octet-stream"
        
        case applicationJSON = "application/json"
        
        case imageJPEG = "image/jpeg"
        case imagePNG = "image/png"
        case imageGIF = "image/gif"
        
        case textPlain = "text/plain"
    }
    
    enum ReservedHTTPHeader: String {
        case contentLength = "Content-Length"
        case authorization = "Authorization"
        case connection = "Connection"
        case host = "Host"
        case proxyAuthenticate = "Proxy-Authenticate"
        case proxyAuthorization = "Proxy-Authorization"
        case wwwAuthenticate = "WWW-Authenticate"
    }
}
