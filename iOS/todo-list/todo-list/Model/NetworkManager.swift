//
//  NetworkManager.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/07.
//


import Foundation

// API docs - https://hooria.herokuapp.com/swagger-ui/index.html#/

class NetworkManager {
    func load<T: Decodable>(url: URL, withCompletion completion: @escaping ([T]?) -> Void) {
        let task = URLSession.shared.dataTask(with: url) { (data, response, error) -> Void in
            guard let data = data else {
                DispatchQueue.main.async { completion(nil) }
                return
            }
            let decoded = try? JSONDecoder().decode([T].self, from: data)
            DispatchQueue.main.async { completion(decoded) }
        }
        task.resume()
    }
}
