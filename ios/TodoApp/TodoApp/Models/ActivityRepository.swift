//
//  NetworkHandler.swift
//  TodoApp
//
//  Created by YEONGJIN JANG on 2022/04/13.
//

import Foundation
import OSLog

//TODO: - ColumnRepository 참고해서 code refactoring
class ActivityRepository {
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
        let dataTask = defaultSession.dataTask(with: request) { (data: Data?, response: URLResponse?, _)  in
            let successRange: Range<Int> = 200..<300
            
            // getting data error
            guard let responseObject = response as? HTTPURLResponse, successRange.contains(responseObject.statusCode) else {
                Logger.view.error("status code: \((response as? HTTPURLResponse)!.statusCode)")
                NotificationCenter.default.post(name: Notification.Name.failedFetch, object: self)
                return
            }
            
            guard let data = data else {
                return
            }

            //원하는 작업
            do {
                let decoder = JSONDecoder()
                let activities = try decoder.decode(Activities.self, from: data)
                NotificationCenter.default.post(name: Notification.Name.successfulFetch, object: self, userInfo: [NotificationKey.activity: activities])
            }
            catch let error {
                print("--> error: \(error.localizedDescription)")
            }
            
        }
        dataTask.resume()
    }
}
