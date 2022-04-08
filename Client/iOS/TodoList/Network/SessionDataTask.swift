//
//  SessionDataTask.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/08.
//

import Foundation

class SessionDataTask: SessionConfiguration {
    
    override init?(
        as string: String,
        using delegate: URLSessionDelegate?,
        in queue: OperationQueue? = nil,
        type: NSURLRequest.NetworkServiceType = .default)
    {
        super.init(as: string, using: delegate, in: queue, type: type)
    }
    
    func requestSessionDataTask(completionHandler: @escaping (Data?)->Void) {
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
    
    func requestSessionDataTask(to url: URL, completionHandler: @escaping (Data?)->Void) {
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
    
    func requestSessionUploadTask(using paramData: Data?, completionHandler: @escaping (Data?)->Void) {
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
    
    func requestSessionUploadTask(to url: URL, using paramData: Data?, completionHandler: @escaping (Data?)->Void) {
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
}
