//
//  MockNetworkRepository.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/05.
//

import Foundation
import Combine

struct MockData {
    let successData: Data = {
        Card.mockData()
    }()
    
    let failureData: Data = {
        "{\"result\":\"id aready exist\", \"status\":\"404\"}".data(using: .utf8)!
    }()
}

class MockURLSessionDataTask: URLSessionDataTask {
    var resumeDidCall: () -> Void = {}

    override func resume() {
        resumeDidCall()
    }
}

class MockURLSession: URLSessionProtocol {
    
    var isRequestSuccess: Bool = true
    var sessionDataTask: MockURLSessionDataTask?
    
    init(isRequestSuccess: Bool = true) {
        self.isRequestSuccess = isRequestSuccess
    }
    
    func dataTask(with request: URLRequest, completionHandler: @escaping (Data?, URLResponse?, Error?) -> Void) -> URLSessionDataTask {
        let successResponse = HTTPURLResponse(url: request.url!, statusCode: 200, httpVersion: "2", headerFields: nil)
        let failureResponse = HTTPURLResponse(url: request.url!, statusCode: 402, httpVersion: "2", headerFields: nil)
        
        let sessionDataTask = MockURLSessionDataTask()
        
        if isRequestSuccess {
            sessionDataTask.resumeDidCall = {
                completionHandler(MockData().successData, successResponse, nil)
            }
        } else {
            sessionDataTask.resumeDidCall = {
                completionHandler(MockData().failureData, failureResponse, nil)
            }
        }
        
        self.sessionDataTask = sessionDataTask
        return sessionDataTask
    }
}
