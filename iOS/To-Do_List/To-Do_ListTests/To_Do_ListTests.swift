//
//  To_Do_ListTests.swift
//  To-Do_ListTests
//
//  Created by 박진섭 on 2022/04/04.
//

import XCTest

class To_Do_ListTests: XCTestCase {
    
    override func setUpWithError() throws {
        try super.setUpWithError()
    }

    override func tearDownWithError() throws {
        TestURLProtocol.loadingHandler = nil
        try super.tearDownWithError()
    }

    func testRequest() {
        //MockData
        guard let path = Bundle.main.path(forResource: "MockJsonData", ofType: "json") else { return }
        guard let jsonString = try? String(contentsOfFile: path) else { return }
        guard let mockdata = jsonString.data(using: .utf8) else { return }
        //Decoded MockData with specific type
        guard let expected = try? JSONDecoder().decode(NetworkResult.self, from: mockdata) else { return }
        
        //mockEndpoint
        let mockEndPoint = Endpoint(httpMethod: .get,
                                    baseURL: "www.mockdata.com",
                                    path: "88888",
                                    headers: ["Content-Type": "application/json"],
                                    body: nil
                                    )
        
        //response 만들기
        TestURLProtocol.loadingHandler = { request in
            let response = HTTPURLResponse(url: request.url!, statusCode: 200, httpVersion: nil, headerFields: nil)!
            return (response,mockdata,nil)
        }
        
        //custom Session
        let expectation = XCTestExpectation(description: "Loading")
        let configuration = URLSessionConfiguration.ephemeral
        configuration.protocolClasses = [TestURLProtocol.self]
        let networkmanager = NetworkManager(session: URLSession(configuration: configuration))
        
        networkmanager.request(endpoint: mockEndPoint)  { (result:Result<NetworkResult,NetworkError>) in
            switch result {
            case .failure(let error):
                XCTFail("Request was not successful: \(error.localizedDescription)")
            case .success(let result):
                XCTAssertEqual(result, expected)
            }
            expectation.fulfill()
        }
        wait(for: [expectation], timeout: 1)
    }
}
