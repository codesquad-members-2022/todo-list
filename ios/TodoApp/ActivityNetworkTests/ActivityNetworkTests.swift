import XCTest
@testable import TodoApp

class ActivityNetworkTests: XCTestCase {

    func testServerReturnData() throws {
        let _ = ActivityRepository.getData(resource: "https://1dc4c2f3-00b4-446d-a22a-d6920eaee622.mock.pstmn.io/history")
        print(Date())
    }
}
