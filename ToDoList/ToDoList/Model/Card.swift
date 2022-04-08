import Foundation

class Card {
    private let id: ID
    var title: String
    var body: String
    var listName: String
    var caption: Caption
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
    
    func moveList(to newListName: String) {
        self.listName = newListName
        self.updatedTime = .now
    }
}
