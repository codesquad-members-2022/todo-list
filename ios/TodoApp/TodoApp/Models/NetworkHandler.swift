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
        guard let url = URL(string: resource) else {
            print("URL is nil")
            return
        }
        // Request
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        
        // DataTask
        
        let dataTask = defaultSession.dataTask(with: request) { (data: Data?, response: URLResponse?, error: Error?)  in
            let successRange = 200..<300
            
            // getting data error
            guard error == nil else {
                Logger.view.error("Error occur: \(String(describing: error))")
                return
            }
            
            guard let data = data, let responseObject = response as? HTTPURLResponse, successRange.contains(responseObject.statusCode) else {
                Logger.view.error("status code: \((response as? HTTPURLResponse)!.statusCode)")
                return
            }
            // 통신에 성공한 경우 data 에 Data 객체가 전달됩니다.
//            guard let jsonToArray = try? JSONSerialization.jsonObject(with: data, options: []) else {
//                Logger.view.error("json to Any Error")
//                return
//            }
            //원하는 작업
            do {
                let decoder = JSONDecoder()
                let activities = try decoder.decode(Activities.self, from: data)
                NotificationCenter.default.post(name: Notification.Name.fetch, object: self, userInfo: [NotificationKey.activity: activities])
            }
            catch let error {
                print("--> error: \(error.localizedDescription)")

            }
            
        }
        dataTask.resume()
    }
}
