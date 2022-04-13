import XCTest
@testable import TodoApp

class ActivityNetworkTests: XCTestCase {

    func testShouldNetworkReturnData() throws {
        let _ = NetworkHandler.getData(resource: "https://1dc4c2f3-00b4-446d-a22a-d6920eaee622.mock.pstmn.io/history")
        sleep(10)
    }
}
