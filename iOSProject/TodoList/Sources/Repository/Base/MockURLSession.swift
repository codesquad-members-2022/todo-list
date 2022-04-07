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
        
        let moveCardTarget = TodoTarget.moveCard(1, toColumn: .done)
        let moveCardURL = moveCardTarget.baseURL.appendingPathComponent(moveCardTarget.path)
        if moveCardURL.path == url.path {
            return Data()
        }
        
        let addCardTarget = TodoTarget.addCard(title: "", body: "")
        let addCardURL = addCardTarget.baseURL.appendingPathComponent(addCardTarget.path)
        if addCardURL.path == url.path {
            return addCardData()
        }
        
        let editCardTarget = TodoTarget.editCard(0, title: "", body: "")
        let editCardURL = editCardTarget.baseURL.appendingPathComponent(editCardTarget.path)
        if editCardURL.path == url.path {
            return editCardData()
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
    
    private func addCardData() -> Data {
        let card = Card(title: "새로만든 카드입니다", body: "새로만든 카드 내용입니다", caption: "author by iOS", orderIndex: 0)
        
        guard let data = try? JSONEncoder().encode(card) else {
            return Data()
        }

        return data
    }
    
    private func editCardData() -> Data {
        let card = Card(title: "수정된 카드입니다", body: "수정된 카드 내용입니다", caption: "author by iOS", orderIndex: 0)
        
        guard let data = try? JSONEncoder().encode(card) else {
            return Data()
        }

        return data
    }
}
