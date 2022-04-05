//
//  NetworkRepository.swift
//  Signup
//
//  Created by seongha shin on 2022/03/28.
//

import Foundation
import Combine

enum HttpError: LocalizedError {
    case unknown
    case httpStatusError(Int, String)
}

class NetworkRepository<Target: BaseTarget> {
    
    private var cancellable: AnyCancellable? = nil
    
    func request<T1: Decodable>(_ target: Target) -> AnyPublisher<T1, APIError> {
        Future<T1, APIError> { promise in
            self.request(target) { (result: Result<T1, APIError>) in
                switch result {
                case .success(let result):
                    promise(.success(result))
                case .failure(let error):
                    promise(.failure(error))
                }
            }
        }.eraseToAnyPublisher()
    }
    
    private func request<T1: Decodable>(_ target: Target, completion: @escaping (Result<T1, APIError>) -> Void) {
        var url = target.baseURL
        url = url.appendingPathComponent(target.path)
        
        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = target.method
        
        if let param = target.parameter,
           let body = try? JSONSerialization.data(withJSONObject: param, options: .init()) {
            urlRequest.httpBody = body
        }
        
        self.cancellable = URLSession.shared
            .dataTaskPublisher(for: urlRequest)
            .filter { data, response in
                guard let response = response as? HTTPURLResponse else {
                    completion(.failure(APIError.unknown))
                    return false
                }
                
                guard 200..<300 ~= response.statusCode else {
                    completion(.failure(APIError.httpStatusError(response.statusCode, response.description)))
                    return false
                }
                
                guard data.isEmpty == false else {
                    completion(.failure(APIError.unknown))
                    return false
                }
                return true
            }
            .map(\.data)
            .decode(type: T1.self, decoder: JSONDecoder())
            .sink(receiveCompletion: { error in
                completion(.failure(APIError.unknown))
            }, receiveValue: { result in
                completion(.success(result))
            })
    }
}
