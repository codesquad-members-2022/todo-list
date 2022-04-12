//
//  JsonConvertTest.swift
//  TodoListTests
//
//  Created by juntaek.oh on 2022/04/12.
//

import XCTest
import os
@testable import TodoList

class JsonConvertTest: XCTestCase {
    override func setUpWithError() throws {
    }

    override func tearDownWithError() throws {
    }

    func testExample() throws {
        func decodeJson<T: Codable>(data: Data) -> [T]?{
            do{
                let result = try JSONDecoder().decode([T].self, from: data)
                return result
            } catch{
                return nil
            }
        }
        
        func testAsync(){
            let url = "https://api.codesquad.kr/signup"
            guard let validURL = URL(string: url) else { return }
            
            let expt = expectation(description: "Can convert Data")
            
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

                let result: [String]? = decodeJson(data: data)
                XCTAssertNotNil(result)
                expt.fulfill()
            }.resume()
            
            waitForExpectations(timeout: 60.0)
        }
        
        testAsync()
    }

    func testPerformanceExample() throws {
        self.measure {
        }
    }

}
