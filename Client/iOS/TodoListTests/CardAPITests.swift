//
//  CardAPITests.swift
//  TodoListTests
//
//  Created by 백상휘 on 2022/04/06.
//

import XCTest
@testable import TodoList

class CardAPITests: XCTestCase {

    let httpRequest = CardHTTPRequest(
        as: "https://www.naver.com",
        using: nil,
        in: OperationQueue.main,
        type: .default
    )
    
    func test_requestInterfaceConnected_getData() throws {
        
        // 현재 API가 전달되지 않아서 URLProtocol로 대신합니다.
        httpRequest?.config.protocolClasses = [MockURLProtocol.self]
        
        let expectation = XCTestExpectation(description: "Wait")
        httpRequest?.doGetRequest(parameter: nil, completionHandler: { taskResult in
            
            guard let _ = try? taskResult.get() else {
                XCTFail("doGetRequest Failed")
                return
            }
            
            expectation.fulfill()
        })
        
        wait(for: [expectation], timeout: 3.0)
    }
}

class MockURLProtocol: URLProtocol {
    var requestHandler: ((URLRequest) throws -> (HTTPURLResponse, Data)) = { request in
        let encoder = JSONEncoder()
        guard let data = try? encoder.encode(["objectKey":"randomKey"]) else {
            XCTFail("URLProtocol handler Failed")
            throw NSError()
        }
        return (HTTPURLResponse(), data)
    }

    override func startLoading() {
        do {
            let (response, data) = try requestHandler(request)
            client?.urlProtocol(self, didReceive: response, cacheStoragePolicy: .notAllowed)
            client?.urlProtocol(self, didLoad: data)
            client?.urlProtocolDidFinishLoading(self)
        } catch {
            client?.urlProtocol(self, didFailWithError: error)
        }
    }
}
