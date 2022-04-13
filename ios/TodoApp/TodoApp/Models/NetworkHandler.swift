//
//  NetworkHandler.swift
//  TodoApp
//
//  Created by YEONGJIN JANG on 2022/04/13.
//

import Foundation
import OSLog

class NetworkHandler {
    class func getData(resource: String) {
        // 세션 환경 설정
        let defaultSession = URLSession(configuration: .default)
        
        guard let url = URL(string: "\(resource)") else {
            print("URL is nil")
            return
        }
        
        // Request
        let request = URLRequest(url: url)
        
        // DataTask
        let dataTask = defaultSession.dataTask(with: request) { (data: Data?, response: URLResponse?, error: Error?) in
            // getting data error
            guard error == nil else {
                Logger.view.error("Error occur: \(String(describing: error))")
                return
            }
            
            guard let data = data, let response = response as? HTTPURLResponse, response.statusCode == 200 else {
                return
            }
            // 통신에 성공한 경우 data 에 Data 객체가 전달됩니다.
            guard let jsonToArray = try? JSONSerialization.jsonObject(with:data, options: []) else {
                Logger.view.error("json to Any Error")
                return
            }
            //원하는 작업
            print(jsonToArray)
        }
    }
}
