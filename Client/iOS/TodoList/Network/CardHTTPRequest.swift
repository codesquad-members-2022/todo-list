//
//  CardHTTPRequest.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

class CardHTTPRequest: SessionDataTask {
    
    func doGetRequest(url: URL, parameter: [String:String]?, completionHandler: @escaping (Data?)->Void) {

        guard let urlComp = makeURLComponents(from: url.absoluteString, using: parameter), let url = urlComp.url else {
            completionHandler(nil)
            return
        }
        
        httpMethod = .GET
        urlString = url.absoluteString
        
        requestSessionDataTask(
            to: url,
            completionHandler: completionHandler
        )
    }
    
    func doGetRequest(parameter: [String:String]?, completionHandler: @escaping (Data?)->Void) {
        
        guard let urlComp = makeURLComponents(using: parameter), let url = urlComp.url else {
            completionHandler(nil)
            return
        }
        
        httpMethod = .GET
        urlString = url.absoluteString
        
        requestSessionDataTask(
            completionHandler: completionHandler
        )
    }
    
    func doPostRequest(url: URL, _ paramData: Data? = nil, completionHandler: @escaping (Data?)->Void) {
        
        httpMethod = .POST
        
        requestSessionUploadTask(
            to: url,
            using: paramData,
            completionHandler: completionHandler
        )
    }
    
    func doPostRequest(_ paramData: Data? = nil, completionHandler: @escaping (Data?)->Void) {
        
        httpMethod = .POST
        
        requestSessionUploadTask(
            using: paramData,
            completionHandler: completionHandler
        )
    }
    
    private func makeURLComponents(from string: String? = nil, using parameter: [String: String]? = nil) -> URLComponents? {
        
        if let string = string {
            urlString = string
        }
        
        guard let url = URL(string: urlString) else { return nil }
        
        var queryItems = [URLQueryItem]()
        
        if let parameter = parameter {
            for param in parameter {
                queryItems.append(URLQueryItem(name: param.key, value: param.value))
            }
        }
        
        var urlComp = URLComponents(url: url, resolvingAgainstBaseURL: false)
        urlComp?.queryItems = queryItems
        return urlComp
    }
}
