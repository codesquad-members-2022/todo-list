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
    
    static func requestGet(url: String) -> Data?{
        guard let validURL = URL(string: url) else { return nil }
        var returnData: Data?
        
        var urlRequest = URLRequest(url: validURL)
        urlRequest.httpMethod = HttpMethod.get.getRawValue()
        
        URLSession.shared.dataTask(with: urlRequest){ data, response, error in
            guard let data = data else { return }
            guard let response = response as? HTTPURLResponse, (200..<300).contains(response.statusCode) else { return }

            returnData = data
        }.resume()
        
        return returnData
    }
    
    //Post // return CardID data
    static func requestPost(url: String, requestParam: Card) -> Data?{
        var requestedID: Data?
        
        guard let uploadData = JsonConverter.encodeJson(param: requestParam) else { return nil }
        
        guard let validURL = URL(string: url) else { return nil }
        var urlRequest = URLRequest(url: validURL)
        urlRequest.httpMethod = HttpMethod.post.getRawValue()
        urlRequest.httpBody = uploadData
        urlRequest.addValue("application/json", forHTTPHeaderField: "Content-Type")
        urlRequest.setValue("\(uploadData.count)", forHTTPHeaderField: "Content-Length")
        
        URLSession.shared.dataTask(with: urlRequest){ data, response, error in
            guard let data = data else { return }
            guard let response = response as? HTTPURLResponse, (200..<300).contains(response.statusCode) else { return }

            requestedID = data
        }.resume()
        
        return requestedID
    }
}

