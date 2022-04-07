//
//  URLSessionProvider.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/06.
//

import Foundation

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
