//
//  todo_listTests.swift
//  todo-listTests
//
//  Created by Jason on 2022/04/04.
//

import XCTest
@testable import todo_list

class todo_listTests: XCTestCase {
    
    let dummyJSONData: Data? = {
        guard let url = Bundle.main.url(forResource: "dummyTaskResponse", withExtension: "json") else { return nil }
        guard let dummyJSONData = try? Data(contentsOf: url) else { return nil }
        return dummyJSONData
    }()
    
    let fractionalSecondsFormatter: ISO8601DateFormatter = {
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate, .withFullTime, .withFractionalSeconds]
        return formatter
    }()
    
    lazy var fractionalSecondsStrategy: JSONDecoder.DateDecodingStrategy = {
        return .custom({ decoder in
            let container = try decoder.singleValueContainer()
            let dateString = try container.decode(String.self)
            
            if let date = self.fractionalSecondsFormatter.date(from: dateString) { return date }
            throw DecodingError.dataCorruptedError(in: container, debugDescription: "Cannot decode date string \(dateString)")
        })
    }()
    
    func testTaskJSONParsing() throws {
        guard let dummyJSONData = dummyJSONData else { return }
        
        let decoder = JSONDecoder()
        decoder.dateDecodingStrategy = fractionalSecondsStrategy
        
        let tasks = try decoder.decode([Task].self, from: dummyJSONData)
        
        XCTAssertEqual(tasks.count, 2)
        
        XCTAssertEqual(tasks[0].content, "This is contents.")
        XCTAssertEqual(tasks[1].content, "This is second contents.")
        
        XCTAssertEqual(tasks[0].createdAt, self.fractionalSecondsFormatter.date(from: "2022-04-08T04:07:49.714Z"))
        XCTAssertEqual(tasks[1].createdAt, self.fractionalSecondsFormatter.date(from: "2022-04-12T05:07:49.714Z"))
        
        XCTAssertEqual(tasks[0].id, 0)
        XCTAssertEqual(tasks[1].id, 1)
        
        XCTAssertEqual(tasks[0].rowPosition, 1)
        XCTAssertEqual(tasks[1].rowPosition, 2)
        
        XCTAssertEqual(tasks[0].status, TaskStatus.done)
        XCTAssertEqual(tasks[1].status, TaskStatus.inProgress)
        
        XCTAssertEqual(tasks[0].device, Device.android)
        XCTAssertEqual(tasks[1].device, Device.iOS)
    }

}
