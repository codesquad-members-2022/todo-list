//
//  URLSession+Extension.swift
//  Photo
//
//  Created by seongha shin on 2022/03/24.
//

import Foundation
import Combine
import UIKit

struct Response<T> {
    let value: T
    let response: URLResponse
}

extension URLSession {
    enum ResponseError: Error {
        case statusCode(HTTPURLResponse)
    }
    
    func networkRequest<T: Decodable>(_ request: URLRequest) -> AnyPublisher<Response<T>, Error> {
        return self.dataTaskPublisher(for: request)
            .tryMap { result in
                let value = try JSONDecoder().decode(T.self, from: result.data)
                return Response(value: value, response: result.response)
                
            }
            .receive(on: RunLoop.main)
            .eraseToAnyPublisher()
    }
    
    private func checkResponse(_ response: URLResponse?) -> ResponseError? {
        if let response = response as? HTTPURLResponse,
           (200..<300).contains(response.statusCode) == false {
            return ResponseError.statusCode(response)
        }
        return nil
    }
}
