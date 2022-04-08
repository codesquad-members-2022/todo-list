//
//  CardHTTPRequest.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

class CardHTTPRequest: SessionConfiguration {
    
    func doGetRequest(url: URL, parameter: [String:String]?, completionHandler: @escaping (Data?)->Void) {

        guard let urlComp = getURLComponents(parameter), let url = urlComp.url else {
            completionHandler(nil)
            return
        }
        
        httpMethod = .GET
        
        getRequestHandler(url: url) { [weak self] request in
            self?.session.dataTask(with: request) { data, response, error in
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(data)
            }.resume()
        }
    }
    
    func doGetRequest(parameter: [String:String]?, completionHandler: @escaping (Data?)->Void) {
        
        guard let urlComp = getURLComponents(from: urlString, parameter), let url = urlComp.url else {
            completionHandler(nil)
            return
        }
        
        httpMethod = .GET
        urlString = url.absoluteString
        
        getCurrentRequestHandler { [weak self] request in
            self?.session.dataTask(with: request) { data, response, error in
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(data)
            }.resume()
        }
    }
    
    private func getURLComponents(from string: String? = nil, _ parameter: [String: String]? = nil) -> URLComponents? {
        
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
    
    func doPostRequest(url: URL, _ paramData: Data? = nil, completionHandler: @escaping (Data?)->Void) {
        
        httpMethod = .POST
        
        getRequestHandler(url: url) { [weak self] request in
            self?.session.uploadTask(with: request, from: paramData) { data, response, error in
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(data)
            }.resume()
        }
    }
    
    func doPostRequest(_ paramData: Data? = nil, completionHandler: @escaping (Data?)->Void) {
        
        httpMethod = .POST
        
        getCurrentRequestHandler { [weak self] request in
            self?.session.uploadTask(with: request, from: paramData) { data, response, error in
                guard let data = data else {
                    completionHandler(nil)
                    return
                }
                
                completionHandler(data)
            }.resume()
        }
    }
}
