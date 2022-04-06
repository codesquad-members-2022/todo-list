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
                completionHandler(MockData().getData(url: request.url), successResponse, nil)
            }
        } else {
            sessionDataTask.resumeDidCall = {
                completionHandler(nil, failureResponse, nil)
            }
        }
        
        self.sessionDataTask = sessionDataTask
        return sessionDataTask
    }
}

class MockData {
    func getData(url: URL?) -> Data {
        guard let url = url else {
            return Data()
        }
        
        let loadColumnURL = TodoTarget.loadColumn.baseURL.appendingPathComponent(TodoTarget.loadColumn.path)
        if loadColumnURL.path == url.path {
            return columnData()
        }
        
        let moveCardURL = TodoTarget.moveCard(1, toColumn: .done).baseURL.appendingPathComponent(TodoTarget.loadColumn.path)
        if moveCardURL.path == url.path {
            return Data()
        }
        
        let deleteCardURL = TodoTarget.deleteCard(1).baseURL.appendingPathComponent(TodoTarget.loadColumn.path)
        if deleteCardURL.path == url.path {
            return Data()
        }
        return Data()
    }
    
    private func columnData() -> Data {
        let cards = (0..<Int.random(in: 2..<20)).map{ index -> Card in
            let title = "테스트타이틀"
            let body = (0..<Int.random(in: 2..<5)).map { _ in "테스트메세지_____테스트메세지_____"}.joined()
            let caption = "author by iOS"

            return Card(title: title, body: body, caption: caption, orderIndex: index)
        }
        
        let column = cards

        guard let body = try? JSONEncoder().encode(column) else {
            return Data()
        }

        return body
    }
}
