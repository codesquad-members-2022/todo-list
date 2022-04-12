//
//  NetworkManagerTests.swift
//  todo-listTests
//
//  Created by Bumgeun Song on 2022/04/11.
//

import XCTest

class NetworkManagerTests: XCTestCase {
    
    private let networkManager = NetworkManager()
    
    func testGetAllTasks() throws {
        let promise = XCTestExpectation(description: "data loaded")
        
        networkManager.getAllTasks { result in
            switch result {
            case .success(let data):
                print(data)
                print("date:", data[0].createdAt)
                promise.fulfill()
            case .failure(let error): print(error.localizedDescription)
            }
        }
        wait(for: [promise], timeout: 2)
    }
    
    func testAdd() throws {
        let promise = XCTestExpectation(description: "data added")
        
        let testContent = "Whasssup. I want very long string. urabitur hendrerit ipsum in turpis pulvinar, sed scelerisque enim eleifend. Mauris volutpat ipsum magna."
        let testTitle = "Test from Eddy!"
        
        let newTask = Task(status: .inProgress, title: testTitle, content: testContent)
        
        networkManager.post(item: newTask) { result in
            switch result {
            case .success(let data):
                XCTAssertEqual(data.title, testTitle)
                XCTAssertEqual(data.content, testContent)
                promise.fulfill()
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
        
        wait(for: [promise], timeout: 1)
    }
}
