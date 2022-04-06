//
//  NetworkRepository.swift
//  Signup
//
//  Created by seongha shin on 2022/03/28.
//

import Foundation
import Combine

enum SessionError: Error {
    case statusCodeError
    case pasingError
    case unknownError
}

protocol URLSessionProtocol {
    func dataTask(with request: URLRequest,completionHandler: @escaping (Data?, URLResponse?, Error?) -> Void) -> URLSessionDataTask
}

extension URLSession: URLSessionProtocol {}

class URLSessionProvider {
    private let session: URLSessionProtocol
    
    init(session: URLSessionProtocol = URLSession.shared) {
        self.session = session
    }
    
    func dataTask(request: URLRequest, completionHandler: @escaping (Result<Data,SessionError>) -> Void) {
        let task = session.dataTask(with: request) { data, response, error in
            guard let httpResponse = response as? HTTPURLResponse,
                  (200..<300).contains(httpResponse.statusCode) else {
                      return completionHandler(.failure(.statusCodeError))
                  }
            
            if let data = data {
                return completionHandler(.success(data))
            }
            
            return completionHandler(.failure(.unknownError))
        }
        
        task.resume()
    }
}

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

