//
//  LogManagerTests.swift
//  todo-listTests
//
//  Created by Jason on 2022/04/12.
//

import XCTest
@testable import todo_list

class LogManagerTests: XCTestCase {
    
//    LogManager를 테스트하려면 API로 부터 Log를 담아올 수 있는지 확인해야하는데
//    서버가 불안정하니 Local환경에서 LogManager가 Log를 담아낼 수 있는 역할에 대해
//    확인이 필요하다... Local은 동기환경...
//
//    Log를 Load해야하는데 dummyData를 활용해야 할 것 같은데...
    private let logManager = LogManager()
    
    func testLoad() {
        logManager.load { logs in
        XCTAssertTrue(logs.count > 0)
        }
    }
    
}
