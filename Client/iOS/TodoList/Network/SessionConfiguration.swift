//
//  SessionConfiguration.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

class SessionConfiguration: SessionCommonAttributes {
    
    // MARK: - Session Need to handle
    private(set) var session: URLSession
    
    // MARK: - Session properties
    
    var config = URLSessionConfiguration.default {
        didSet {
            resetSession()
        }
    }
    
    var networkServiceType: NSURLRequest.NetworkServiceType
    {
        didSet {
            resetSession()
        }
    }
    var sessionDelegate: URLSessionDelegate?
    {
        didSet {
            resetSession()
        }
    }
    var sessionQueue: OperationQueue?
    {
        didSet {
            resetSession()
        }
    }
    
    // MARK: - Initializer
    init?(
        as string: String,
        using delegate: URLSessionDelegate?,
        in queue: OperationQueue? = nil,
        type: NSURLRequest.NetworkServiceType = .default)
    {
        
        self.networkServiceType = type
        config.networkServiceType = networkServiceType
        
        session = URLSession(configuration: config, delegate: delegate, delegateQueue: queue)
        
        super.init(as: string)
    }
    
    // MARK: - Utility
    private func resetSession()
    {
        config.networkServiceType = networkServiceType
        session = URLSession(
            configuration: config,
            delegate: sessionDelegate,
            delegateQueue: sessionQueue
        )
    }
}
