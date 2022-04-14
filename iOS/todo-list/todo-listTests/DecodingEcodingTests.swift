//
//  DecodingEcodingTests.swift
//  todo-listTests
//
//  Created by Bumgeun Song on 2022/04/11.
//

import XCTest

class DecodingEcodingTests: XCTestCase {
    
    let dummyTaskJSONData: Data? = {
        guard let url = Bundle.main.url(forResource: "dummyTaskResponse", withExtension: "json") else { return nil }
        guard let dummyJSONData = try? Data(contentsOf: url) else { return nil }
        return dummyJSONData
    }()
    
    let dummyLogJSONData: Data? = {
        guard let url = Bundle.main.url(forResource: "dummyLogResponse", withExtension: "json") else { return nil }
        guard let dummyJSONData = try? Data(contentsOf: url) else { return nil }
        return dummyJSONData
    }()
    
    let decoder = JSONDecoder().krISO8601
    let encoder = JSONEncoder().krISO8601
    
    func testTaskJSONParsing() throws {
        guard let dummyJSONData = dummyTaskJSONData else { return }
        
        let tasks = try self.decoder.decode([Task].self, from: dummyJSONData)
        
        XCTAssertEqual(tasks.count, 4)
        
        XCTAssertEqual(tasks[0].content, "content1")
        XCTAssertEqual(tasks[1].content, "content2")
    }
    
    func testLogJSONParsing() throws {
        guard let dummyLogJSONData = dummyLogJSONData else { return }

        let logs = try self.decoder.decode([Log].self, from: dummyLogJSONData)
        XCTAssertTrue(logs.count > 0)
    }
    
    func testTaksJSONEncoding() throws {
        guard let dummyJSONData = dummyTaskJSONData else { return }
        
        let tasks = try self.decoder.decode([Task].self, from: dummyJSONData)
        
        let encoder = JSONEncoder()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        encoder.dateEncodingStrategy = .formatted(dateFormatter)
        
        guard let encoded = try? self.encoder.encode(tasks) else { return }
        
        print(String(data: encoded, encoding: .utf8)!)

    }
}
