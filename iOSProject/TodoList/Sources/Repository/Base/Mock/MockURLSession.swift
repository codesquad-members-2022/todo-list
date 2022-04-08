//
//  MockNetworkRepository.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine

class MockURLSessionDataTask: URLSessionDataTask {
    var resumeDidCall: () -> Void = {}

    override func resume() {
        resumeDidCall()
    }
}

class MockURLSession: URLSessionProtocol {
    static let shared: MockURLSession = MockURLSession()
    
    var isRequestSuccess: Bool = true
    private let dataBase = MockDataBase()
    
    func dataTask(with request: URLRequest, completionHandler: @escaping (Data?, URLResponse?, Error?) -> Void) -> URLSessionDataTask {
        let sessionDataTask = MockURLSessionDataTask()
        
        let result = dataBase.databaseProcess(urlRequest: request)
        
        sessionDataTask.resumeDidCall = {
            completionHandler(result.0, result.1, nil)
        }
        return sessionDataTask
    }
}
