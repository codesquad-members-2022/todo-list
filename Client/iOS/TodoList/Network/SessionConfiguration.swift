//
//  SessionConfiguration.swift
//  TodoList
//
//  Created by 백상휘 on 2022/04/05.
//

import Foundation

/// 세션을 생성하고 관리합니다.
///
/// - session: dataTask 요청 시 사용하는 URLSession입니다.
/// - config: URLSession의 configuration으로 포함될 객체입니다.
/// - networkServiceType: 네트워크 리소스를 사용하는 방법을 정의합니다. 업데이트 되면 자동으로 session도 새로 생성됩니다.
/// - sessionDelegate: URLSession의 delegate입니다. 업데이트 되면 자동으로 session도 새로 생성됩니다.
/// - sessionQueue: delegate 메소드가 실행될 Queue입니다. 업데이트 되면 자동으로 session도 새로 생성됩니다.
class SessionConfiguration: SessionCommonAttributes {
    
    private(set) var session: URLSession
    let config = URLSessionConfiguration.default
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
