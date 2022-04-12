//
//  CardAPITests.swift
//  TodoListTests
//
//  Created by 백상휘 on 2022/04/06.
//

import XCTest
@testable import TodoList

class CardAPITests: XCTestCase {
    
    struct MockAPI: ServerAPI {
        let endpoint: String = "https://naver.com"
    }
    
    var task: DataTask!
    
    override func setUpWithError() throws {
        let task = DataTask(api: Team13API())
        self.task = task
    }

    override func tearDownWithError() throws {
        self.task = nil
    }
    
    func test_userAPI_connectionSuccess_getCookie() {
        let expectation = XCTestExpectation()
        task.fetchUser { result in
            switch result {
            case .success(let userId):
                XCTAssertNotNil(userId)
            case .failure(let error):
                XCTFail("Failed get user cookie \(error)")
            }
            expectation.fulfill()
        }
        wait(for: [expectation], timeout: 3.0)
    }
    
    
    func test_todolistAPI_connectionSuccess_getData() throws {
        let expectation = XCTestExpectation()
        task?.fetchAll(dataType: Cards.self, completionHandler: { result in
            switch result {
            case .success(let data):
                XCTAssertNotNil(data)
            case .failure(let error):
                XCTFail("todolist request Failed \(error.localizedDescription)")
            }
            expectation.fulfill()
        })
        wait(for: [expectation], timeout: 3.0)
    }
    
    
    func test_requestInterfaceConnected_getData() throws {
        let request = CardHTTPRequest(
            as: "http://13.125.216.180:8080/todolist/3",
            using: nil,
            in: OperationQueue.main,
            type: .default
        )

        let expectation = XCTestExpectation()
        request?.doGetRequest(parameter: nil, completionHandler: { result in
            switch result {
            case .success(let data):
                XCTAssertNotNil(data)
            case .failure(let error):
                XCTFail("todolist request Failed")
            }
            expectation.fulfill()
        })
        wait(for: [expectation], timeout: 2.0)
    }
    
    func test_requestInterfaceConnected_getMockData() throws {
        let httpRequest = CardHTTPRequest(
            as: "https://www.naver.com",
            using: nil,
            in: OperationQueue.main,
            type: .default
        )
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
