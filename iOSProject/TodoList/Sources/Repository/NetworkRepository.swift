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
    
    func request(_ target: Target, isSucccess: Bool) -> AnyPublisher<NetworkResult, Never> {
        let session = MockURLSession.shared
        session.isRequestSuccess = isSucccess
        return request(target, session: session)
    }
    
    func request(_ target: Target, session: URLSessionProtocol = URLSession.shared ) -> AnyPublisher<NetworkResult, Never> {
        provider = URLSessionProvider(session: session)
        
        var url = target.baseURL
        url = url.appendingPathComponent(target.path)
        
        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = target.method

        if let param = target.parameter,
           let body = try? JSONSerialization.data(withJSONObject: param, options: .init()) {
            urlRequest.httpBody = body
        }
        
        return Future<NetworkResult, Never> { promise in
            self.provider?.dataTask(request: urlRequest) { result in
                promise(.success(result))
            }
        }.eraseToAnyPublisher()
    }
}

