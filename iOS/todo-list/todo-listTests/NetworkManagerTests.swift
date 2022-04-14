//
//  NetworkManagerTests.swift
//  todo-listTests
//
//  Created by Bumgeun Song on 2022/04/11.
//

import XCTest
import OSLog

class NetworkManagerTests: XCTestCase {
    
    private let networkManager = NetworkManager()
    
    func testGetAllTasks() throws {
        let promise = XCTestExpectation(description: "data loaded")
        
        networkManager.getAllTasks { result in
            switch result {
            case .success(let data):
                XCTAssertTrue(data.count > 0)
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
    
    func testDelete() throws {
        // Delete는 멱등하지가 않은데, Test 실행시마다 test id를 어떻게 동적으로 정해야할까?
        let testId = 2
        
        let promise = XCTestExpectation(description: "data deleted")
        
        networkManager.delete(id: testId) { result in
            switch result {
            case .success(let id):
                XCTAssertEqual(id, testId)
                promise.fulfill()
            case .failure(let error): print(error.localizedDescription)
            }
        }
        
        wait(for: [promise], timeout: 1)
    }
}
