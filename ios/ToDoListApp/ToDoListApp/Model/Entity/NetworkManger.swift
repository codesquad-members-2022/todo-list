//
//  NetworkManager.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/13.
//

import Foundation

class NetworkManager<T: Codable> {
    
    private var session: URLSession
    
    init(session: URLSession) {
        self.session = session
    }
    
    func post(url: URL?, data: T, completion: @escaping(Result<T, NetworkError>) -> Void) {
        
        guard let url = url else {
            return completion(.failure(.invalidURL))
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = HTTPMethod.post.description
        request.addValue(HTTPHeader.applicationJson, forHTTPHeaderField: HTTPHeader.ContentType)
        
        do {
            let data = try JSONEncoder().encode(data)
            request.httpBody = data
        } catch {
            return completion(.failure(.encodingError))
        }
        
        let task = URLSession.shared.dataTask(with: request) { _, response, error in
            
            if error != nil {
                return completion(.failure(.error))
            }
            
            guard let httpURLResponse = response as? HTTPURLResponse else {
                return completion(.failure(.invalidResponse))
            }
            
            guard (200...299) ~= httpURLResponse.statusCode else {
                return completion(.failure(.invalidStatusCode(code: httpURLResponse.statusCode)))
            }
            
            return completion(.success(data))
        }
        
        task.resume()
    }
    
    func get(url: URL?, completion: @escaping(Result<[T], NetworkError>) -> Void) {
        
        guard let url = url else {
            return completion(.failure(.invalidURL))
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = HTTPMethod.get.description
        request.addValue(HTTPHeader.applicationJson, forHTTPHeaderField: HTTPHeader.ContentType)
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            if error != nil {
                return completion(.failure(.error))
            }
            
            guard let httpURLResponse = response as? HTTPURLResponse else {
                return completion(.failure(.invalidResponse))
            }
            
            guard (200...299) ~= httpURLResponse.statusCode else {
                return completion(.failure(.invalidStatusCode(code: httpURLResponse.statusCode)))
            }
            
            guard let data = data else {
                return completion(.failure(.emptyData))
            }

            let decoder = JSONDecoder()

            do {
                let data = try decoder.decode([T].self, from: data)
                return completion(.success(data))
            } catch {
                return completion(.failure(.decodingError))
            }
        }
        
        task.resume()
    }
}

