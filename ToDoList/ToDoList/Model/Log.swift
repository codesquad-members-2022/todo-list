import Foundation

struct Log {
    private let id: ID
    var userName: String
    var body: String
    private let createdTime: Date
    
    init(name: String, body: String) {
        self.id = ID()
        self.userName = name
        self.body = body
        self.createdTime = Date.now
    }
}
