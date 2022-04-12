//
//  TaskManagerTests.swift
//  todo-listTests
//
//  Created by Bumgeun Song on 2022/04/11.
//

import XCTest

class TaskManagerTests: XCTestCase {
    
    func testLoad() {
        let promise = XCTestExpectation(description: "Data Loaded")
        TaskManager.shared.load { tasks in
            XCTAssertTrue(tasks.count > 0)
            promise.fulfill()
        }
        wait(for: [promise], timeout: 1)
    }
    
    func testAdd() {
        let promise = XCTestExpectation(description: "Data Added")
        
        let testContent = "Whasssup. I want very long string. urabitur hendrerit ipsum in turpis pulvinar, sed scelerisque enim eleifend. Mauris volutpat ipsum magna."
        let testTitle = "Test from Eddy!"
        
        TaskManager.shared.add(state: .todo, title: testTitle, content: testContent) { task in
            XCTAssertEqual(task.title, testTitle)
            XCTAssertEqual(task.content, testContent)
            promise.fulfill()
        }
        
        wait(for: [promise], timeout: 1)
    }

}
