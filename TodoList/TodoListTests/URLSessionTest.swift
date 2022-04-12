//
//  TodoListTests.swift
//  TodoListTests
//
//  Created by juntaek.oh on 2022/04/12.
//

import XCTest
import os

class TodoListTests: XCTestCase {
    override func setUpWithError() throws {
    }

    override func tearDownWithError() throws {
    }

    func testExample() throws {
        func testAsync(){
            let url = "https://api.codesquad.kr/signup"
            guard let validURL = URL(string: url) else { return }
            
            let expt = expectation(description: "Can get Data")
            var returnData: Data?
            
            var urlRequest = URLRequest(url: validURL)
            urlRequest.httpMethod = "GET"
            
            URLSession.shared.dataTask(with: urlRequest){ data, response, error in
                guard let data = data else {
                    os_log("Can't get data")
                    return
                }
                guard let response = response as? HTTPURLResponse, (200..<300).contains(response.statusCode) else {
                    os_log("Response is not available")
                    return
                }

                returnData = data
                expt.fulfill()
            }.resume()
            
            waitForExpectations(timeout: 60.0)
            XCTAssertNotNil(returnData)
        }
        
        testAsync()
    }

    func testPerformanceExample() throws {
        measure {
        }
    }

}
