import Foundation
import MobileCoreServices

final class Card: NSObject, Cardable, Codable {

    private enum CodingKeys: String, CodingKey {
        case id
        case title = "subject"
        case body = "contents"
        case cardListID = "columnId"
        case caption = "author"
        case createdTime = "createdAt"
    }
    
    private(set) var id: Int = -1
    private(set) var title: String
    private(set) var body: String
    private(set) var cardListID: Int
    private(set) var caption: Caption
    private(set) var createdTime: Date
    
    init(title: String, body: String, caption: Caption, cardListID: Int) {
        self.title = title
        self.body = body
        self.caption = caption
        self.cardListID = cardListID
        self.createdTime = Date.now
    }
    
    required init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        
        self.id = try container.decode(Int.self, forKey: .id)
        self.title = try container.decode(String.self, forKey: .title)
        self.body = try container.decode(String.self, forKey: .body)
        self.cardListID = try container.decode(Int.self, forKey: .cardListID)
        self.caption = try container.decode(Caption.self, forKey: .caption)
        self.createdTime = try container.decode(Date.self, forKey: .createdTime)
    }
    
    func moveList(to newCardListID: Int) {
        self.cardListID = newCardListID
    }
    
    func setID(with id: Int) {
        self.id = id
    }
}

extension Card: ModelFactoriable {
    static func make(title: String, body: String, data: [Any]) -> Cardable {
        let cardListID: Int = data[0] as? Int ?? -1
        return Card.init(title: title, body: body, caption: .iOS, cardListID: cardListID)
    }
}

extension Card: NSItemProviderWriting {
    static var writableTypeIdentifiersForItemProvider: [String] {
        return [String(kUTTypeData)]
    }
    
    func loadData(withTypeIdentifier typeIdentifier: String, forItemProviderCompletionHandler completionHandler: @escaping (Data?, Error?) -> Void) -> Progress? {
        let progress = Progress(totalUnitCount: 100)
        
        do {
            let data = try JSONEncoder().encode(self)
            progress.completedUnitCount = 100
            completionHandler(data, nil)
        } catch {
            completionHandler(nil, error)
        }
        
        return progress
    }
}

extension Card: NSItemProviderReading {
    static var readableTypeIdentifiersForItemProvider: [String] {
        return [String(kUTTypeData)]
    }
    
    static func object(withItemProviderData data: Data, typeIdentifier: String) throws -> Card {
        let decoder = JSONDecoder()
        do {
            let card = try decoder.decode(Card.self, from: data)
            return card
        }
    }
}
