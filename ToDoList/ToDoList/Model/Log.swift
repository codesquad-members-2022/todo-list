import Foundation

struct Log {
    private var id: Int?
    var userName: String
    var body: String
    private let createdTime: Date
    
    init(name: String, body: String) {
        self.userName = name
        self.body = body
        self.createdTime = Date.now
    }
}
