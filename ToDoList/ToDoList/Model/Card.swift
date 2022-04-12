import Foundation

struct Card: Cardable, Codable {

    private enum CodingKeys: String, CodingKey {
        case id
        case title
        case body
        case listName
        case caption
        case createdTime
        case updatedTime
    }
    
    private(set) var id: Int?
    private(set) var title: String
    private(set) var body: String
    private(set) var listName: String
    private(set) var caption: Caption
    private let createdTime: Date
    private var updatedTime: Date
    
    init(title: String, body: String, caption: Caption, listName: String) {
        self.title = title
        self.body = body
        self.caption = caption
        self.listName = listName
        self.createdTime = Date.now
        self.updatedTime = Date.now
    }
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        
        self.id = try container.decode(Int.self, forKey: .id)
        self.title = try container.decode(String.self, forKey: .title)
        self.body = try container.decode(String.self, forKey: .body)
        self.listName = try container.decode(String.self, forKey: .listName)
        self.caption = try container.decode(Caption.self, forKey: .caption)
        self.createdTime = try container.decode(Date.self, forKey: .createdTime)
        self.updatedTime = try container.decode(Date.self, forKey: .updatedTime)
    }
    
    mutating func moveList(to newListName: String) {
        self.listName = newListName
        self.updatedTime = .now
    }
    
    mutating func setID(with id: Int) {
        self.id = id
    }
}

extension Card: ModelFactoriable {
    static func make(title: String, body: String, data: [Any]) -> Cardable {
        let listName: String = data[0] as? String ?? ""
        return Card.init(title: title, body: body, caption: .iOS, listName: listName)
    }
}
