//
//  SessionDataTask.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/08.
//

import Foundation

typealias SessionTaskError = SessionDataTask.ErrorInTask

class SessionDataTask: SessionConfiguration {
    
    override init?(
        as string: String,
        using delegate: URLSessionDelegate?,
        in queue: OperationQueue? = nil,
        type: NSURLRequest.NetworkServiceType = .default)
    {
        super.init(as: string, using: delegate, in: queue, type: type)
    }
    
    func requestSessionDataTask(completionHandler: @escaping (Result<Data, SessionTaskError>)->Void) {
        getCurrentRequestHandler { [weak self] request in
            self?.session.dataTask(with: request) { data, response, error in
                guard let data = data else {
                    completionHandler(Result.failure(.dataTask))
                    return
                }
                
                completionHandler(Result.success(data))
            }.resume()
        }
    }
    
    func requestSessionDataTask(to url: URL, completionHandler: @escaping (Result<Data, SessionTaskError>)->Void) {
        getRequestHandler(url: url) { [weak self] request in
            self?.session.dataTask(with: request) { data, response, error in
                guard let data = data else {
                    completionHandler(Result.failure(.dataTask))
                    return
                }
                
                completionHandler(Result.success(data))
            }.resume()
        }
    }
    
    func requestSessionUploadTask(using paramData: Data?, completionHandler: @escaping (Result<Data, SessionTaskError>)->Void) {
        getCurrentRequestHandler { [weak self] request in
            self?.session.uploadTask(with: request, from: paramData) { data, response, error in
                guard let data = data else {
                    completionHandler(Result.failure(.uploadTask))
                    return
                }
                
                completionHandler(Result.success(data))
            }.resume()
        }
    }
    
    func requestSessionUploadTask(to url: URL, using paramData: Data?, completionHandler: @escaping (Result<Data, SessionTaskError>)->Void) {
        getRequestHandler(url: url) { [weak self] request in
            self?.session.uploadTask(with: request, from: paramData) { data, response, error in
                guard let data = data else {
                    completionHandler(Result.failure(.uploadTask))
                    return
                }
                
                completionHandler(Result.success(data))
            }.resume()
        }
    }
    
    enum ErrorInTask: Error {
        case dataTask
        case uploadTask
    }
}
