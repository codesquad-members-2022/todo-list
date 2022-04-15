import Foundation

struct Activity: Codable {
    let action: String
    let cardID: Int
    let cardTitle: String
    let oldColumn: Int
    let oldColumnName: String
    let newColumn: Int
    let newColumnName, createdDate, modifiedDate: String

    enum CodingKeys: String, CodingKey {
        case action
        case cardID = "cardId"
        case cardTitle, oldColumn, oldColumnName, newColumn, newColumnName, createdDate, modifiedDate
    }
}

typealias Activities = [Activity]
