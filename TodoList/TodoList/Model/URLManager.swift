//
//  URLManager.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/07.
//

import Foundation


class URLManager{
    enum HttpMethod: String{
        case get = "GET"
        case post = "POST"
        case patch = "PATCH"
        case delete = "DELETE"
        
        func getRawValue() -> String{
            return self.rawValue
        }
    }
    
    static func requestGet(url: String) -> [Card]?{
        guard let validURL = URL(string: url) else { return nil}
        var urlRequest = URLRequest(url: validURL)
        var decodeData: [Card]?
        
        urlRequest.httpMethod = HttpMethod.get.getRawValue()
        URLSession.shared.dataTask(with: urlRequest){ data, response, error in
            guard let data = data else { return }
            do {
                guard let response = response as? HTTPURLResponse, (200..<300).contains(response.statusCode) else {
                    return
                } // GET 응답 처리
                    let decoder = try JSONDecoder().decode([Card].self ,from: data)
                    decodeData = decoder
            } catch{
                return
            }
        }
        return decodeData
    }
    
    //Post // return CardID data
    static func requestPost(url: String, requestParam: Card) -> Int?{
        var requestedID: Int?
        let paramData = [requestParam.title, requestParam.content, requestParam.userID]
        
        guard let uploadData = try? JSONEncoder().encode(paramData) else { return nil }
        
        guard let validURL = URL(string: url) else { return nil }
        var urlRequest = URLRequest(url: validURL)
        urlRequest.httpMethod = HttpMethod.post.getRawValue()
        
        URLSession.shared.uploadTask(with: urlRequest, from: uploadData){ data, response, error in
            guard let data = data else { return }
            do {
                guard let response = response as? HTTPURLResponse, (200..<300).contains(response.statusCode) else {
                    return
                }
                let decoder = try JSONDecoder().decode(Int.self,from: data)
                requestedID = decoder
            } catch{
                return
            }
        }
        return requestedID
    }
    
}

