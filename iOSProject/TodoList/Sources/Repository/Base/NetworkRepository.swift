//
//  NetworkRepository.swift
//  Signup
//
//  Created by seongha shin on 2022/03/28.
//

import Foundation
import Combine

class NetworkRepository<Target: BaseTarget> {
    private let provider = URLSessionProvider(session: MockURLSession(isRequestSuccess: true))
    
    func request<T: Decodable>(_ target: Target) -> AnyPublisher<T, SessionError> {
        var url = target.baseURL
        url = url.appendingPathComponent(target.path)
        
        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = target.method

        if let param = target.parameter,
           let body = try? JSONSerialization.data(withJSONObject: param, options: .init()) {
            urlRequest.httpBody = body
        }
        
        return Future<T, SessionError> { promise in
            self.provider.dataTask(request: urlRequest) { result in
                switch result {
                case .success(let data):
                    guard let dto = try? JSONDecoder().decode(T.self, from: data) else {
                        promise(.failure(.pasingError))
                        return
                    }
                    promise(.success(dto))
                case .failure(let error):
                    promise(.failure(error))
                }
            }
        }.eraseToAnyPublisher()
    }
}

