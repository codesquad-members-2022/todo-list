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
<<<<<<< HEAD
<<<<<<< HEAD
        
        columnViewModel.list.bind { taskViewModels in
            XCTAssertTrue(taskViewModels.count > 0)
            promise.fulfill()
=======

        columnViewModel.list.bind { taskViewModels in
                print(taskViewModels.count)
                XCTAssertTrue(taskViewModels.count > 0)
                promise.fulfill()
>>>>>>> 60d70e9 (🐛: ViewModel에서 nil이 넘어오는 버그를 Observable의 bind와 클로저 실행 분리로 해결)
=======
        
        columnViewModel.list.bind { taskViewModels in
            print(taskViewModels.count)
            XCTAssertTrue(taskViewModels.count > 0)
            promise.fulfill()
>>>>>>> c862819 (⚡️: KanbanViewController에 할일 조회 및 추가 기능 연결)
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
    
    func testDelete() throws {
        let columnViewModel = ColumnViewModel(state: .inProgress, taskManager: TaskManager())
        
        // Test id를 얻기 위한 load 실행
        let loadPromise = XCTestExpectation(description: "data loaded")
        var testId = 0
        
        columnViewModel.list.bind { taskViewModels in
            testId = taskViewModels[0].id
            loadPromise.fulfill()
        }
        
        columnViewModel.load()
        wait(for: [loadPromise], timeout: 1)
        
        // 가져온 Test id로 delete 테스트 실행
        let deletePromise = XCTestExpectation(description: "data deleted")
        
        columnViewModel.list.bind { taskViewModels in
            XCTAssertTrue(taskViewModels.filter { $0.id == testId }.count == 0)
            deletePromise.fulfill()
        }
        
        columnViewModel.delete(id: testId)
        
        wait(for: [deletePromise], timeout: 1)
    }
}
