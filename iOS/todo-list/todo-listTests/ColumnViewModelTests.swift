//
//  ColumnViewModelTests.swift
//  todo-listTests
//
//  Created by Bumgeun Song on 2022/04/11.
//

import XCTest

class ColumnViewModelTests: XCTestCase {
    
    func testLoad() {
        let promise = XCTestExpectation(description: "Data Loaded")
        let columnViewModel = ColumnViewModel(state: .todo, taskManager: TaskManager())
        
        columnViewModel.list.bind { taskViewModels in
            print(taskViewModels.count)
            XCTAssertTrue(taskViewModels.count > 0)
            promise.fulfill()
        }
        
        columnViewModel.load()
        
        wait(for: [promise], timeout: 2)
    }
    
    func testAdd() {
        let promise = XCTestExpectation(description: "Data Loaded")
        let columnViewModel = ColumnViewModel(state: .done, taskManager: TaskManager())
        
        columnViewModel.list.bind { taskViewModels in
            print(taskViewModels.count)
            XCTAssertTrue(taskViewModels.count > 0)
            promise.fulfill()
        }
        
        
        let testContent = "Whasssup. I want very long string. urabitur hendrerit ipsum in turpis pulvinar, sed scelerisque enim eleifend."
        let testTitle = "This is another Test from Eddy!"
        
        columnViewModel.add(title: testTitle, content: testContent)
        
        wait(for: [promise], timeout: 2)
    }
    
}
