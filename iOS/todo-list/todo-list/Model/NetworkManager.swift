//
//  NetworkManager.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/07.
//


import Foundation

// API docs - https://hooria.herokuapp.com/swagger-ui/index.html#/

class NetworkManager {
    let urlSession = URLSession.shared
    
    func get<T: Decodable>(to url: URL, then completion: @escaping ([T]?) -> Void) {
        let task = urlSession.dataTask(with: url) { (data, response, error) -> Void in
            guard let data = data else {
                DispatchQueue.main.async { completion(nil) }
                return
            }
            let decoded = try? JSONDecoder().decode([T].self, from: data)
            DispatchQueue.main.async { completion(decoded) }
        }
        task.resume()
    }
    
    func post(to url: URL, body: Data, then completion: @escaping (Error?) -> Void) {
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.httpBody = body
        
        let dataTask = urlSession.dataTask(with: request) { data, response, error in
            DispatchQueue.main.async { completion(error) }
        }
        
        dataTask.resume()
    }
    
    func delete(to url: URL, then completion: @escaping (Error?) -> Void) {
        var request = URLRequest(url: url)
        request.httpMethod = "DELETE"
        
        let dataTask = urlSession.dataTask(with: request) { data, response, error in
            DispatchQueue.main.async { completion(error) }
        }
        
        dataTask.resume()
    }
    
    func patch(to url: URL, body: Data, then completion: @escaping (Error?) -> Void) {
        var request = URLRequest(url: url)
        
        request.httpMethod = "PATCH"
        request.httpBody = body
        
        let dataTask = urlSession.dataTask(with: request) { data, response, error in
            DispatchQueue.main.async { completion(error) }
        }
        
        dataTask.resume()
    }
}
