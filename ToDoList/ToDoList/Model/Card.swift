import Foundation

struct Card: Cardable {
    private let id: ID
    private(set) var title: String
    private(set) var body: String
    private(set) var listName: String
    private(set) var caption: Caption
    private let createdTime: Date
    private var updatedTime: Date
    
    init(title: String, body: String, caption: Caption, listName: String) {
        self.id = ID()
        self.title = title
        self.body = body
        self.caption = caption
        self.listName = listName
        self.createdTime = Date.now
        self.updatedTime = Date.now
    }
    
    mutating func moveList(to newListName: String) {
        self.listName = newListName
        self.updatedTime = .now
    }
}
