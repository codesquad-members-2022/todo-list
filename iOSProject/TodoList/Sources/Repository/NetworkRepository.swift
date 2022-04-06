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
    
    func request<T: Decodable>(_ target: Target, isSucccess: Bool) -> AnyPublisher<Result<T, SessionError>, Never> {
        self.request(target, session: MockURLSession(isRequestSuccess: isSucccess))
    }
    
    func request<T: Decodable>(_ target: Target, session: URLSessionProtocol = URLSession.shared ) -> AnyPublisher<Result<T, SessionError>, Never> {
        provider = URLSessionProvider(session: session)
        
        var url = target.baseURL
        url = url.appendingPathComponent(target.path)
        
        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = target.method

        if let param = target.parameter,
           let body = try? JSONSerialization.data(withJSONObject: param, options: .init()) {
            urlRequest.httpBody = body
        }
        
        return Future<Result<T, SessionError>, Never> { promise in
            self.provider?.dataTask(request: urlRequest) { result in
                switch result {
                case .success(let data):
                    guard let dto = try? JSONDecoder().decode(T.self, from: data) else {
                        return
                    }
                    promise(.success(.success(dto)))
                case .failure(let error):
                    promise(.success(.failure(error)))
                    return
                }
            }
        }.eraseToAnyPublisher()
    }
}

