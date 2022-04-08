//
//  NetworkRepository.swift
//  Signup
//
//  Created by seongha shin on 2022/03/28.
//

import Foundation
import Combine

class NetworkRepository<Target: BaseTarget> {
    private var provider: URLSessionProvider?
    
    func request(_ target: Target, isSucccess: Bool) -> AnyPublisher<Result<Data, SessionError>, Never> {
        let session = MockURLSession.shared
        session.isRequestSuccess = isSucccess
        return request(target, session: session)
    }
    
    func request(_ target: Target, session: URLSessionProtocol = URLSession.shared ) -> AnyPublisher<Result<Data, SessionError>, Never> {
        provider = URLSessionProvider(session: session)
        
        var url = target.baseURL
        url = url.appendingPathComponent(target.path)
        
        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = target.method

        if let param = target.parameter,
           let body = try? JSONSerialization.data(withJSONObject: param, options: .init()) {
            urlRequest.httpBody = body
        }
        
        return Future<Result<Data, SessionError>, Never> { promise in
            self.provider?.dataTask(request: urlRequest) { result in
                promise(.success(result))
            }
        }.eraseToAnyPublisher()
    }
}

