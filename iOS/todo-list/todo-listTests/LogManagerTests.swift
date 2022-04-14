//
//  LogManagerTests.swift
//  todo-listTests
//
//  Created by Jason on 2022/04/12.
//

import XCTest
@testable import todo_list

class LogManagerTests: XCTestCase {
    
    private let logManager = LogManager()
    
    func testLoad() {
        logManager.load { logs in
        XCTAssertTrue(logs.count > 0)
        }
    }
    
}
