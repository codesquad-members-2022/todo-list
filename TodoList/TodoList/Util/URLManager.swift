//
//  URLManager.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/07.
//

import Foundation

final class URLManager{
    enum HttpMethod: String{
        case get = "GET"
        case post = "POST"
        case patch = "PATCH"
        case delete = "DELETE"
        
        func getRawValue() -> String{
            return self.rawValue
        }
    }
    
    static func requestGet(url: String, complete: @escaping (Data) -> ()){
        guard let validURL = URL(string: url) else { return }
        
        var urlRequest = URLRequest(url: validURL)
        urlRequest.httpMethod = HttpMethod.get.getRawValue()
        
        URLSession.shared.dataTask(with: urlRequest){ data, response, error in
            guard let data = data else { return }
            guard let response = response as? HTTPURLResponse, (200..<300).contains(response.statusCode) else { return }
            
            complete(data)
        }.resume()
    }
    
    //Post - encode된 Data를 param 인자 값으로 받아옴
    static func requestPost(url: String, encodingData: Data, complete: @escaping (Data) -> ()){
        guard let validURL = URL(string: url) else { return }
        
        var urlRequest = URLRequest(url: validURL)
        urlRequest.httpMethod = HttpMethod.post.getRawValue()
        urlRequest.httpBody = encodingData
        urlRequest.addValue("application/json", forHTTPHeaderField: "Content-Type")
        urlRequest.setValue("\(encodingData.count)", forHTTPHeaderField: "Content-Length")
        
        URLSession.shared.dataTask(with: urlRequest){ data, response, error in
            guard let data = data else { return }
            guard let response = response as? HTTPURLResponse, (200..<300).contains(response.statusCode) else { return }

            complete(data)
        }.resume()
    }
}

