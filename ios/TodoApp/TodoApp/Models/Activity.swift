import Foundation

struct Activity: Codable {
    let action: String
    let id, oldColumn, newColumn: Int
    let modifiedAt: String

    enum CodingKeys: String, CodingKey {
        case action
        case id = "cardId"
        case oldColumn, newColumn
        case modifiedAt = "modifiedDate"
    }
}


