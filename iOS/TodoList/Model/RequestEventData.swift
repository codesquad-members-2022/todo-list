import Foundation

struct RequestEventData: Codable {
    private(set) var id: Int
    private(set) var cardTitle: String
    private(set) var prevSection: String
    private(set) var currentSection: String
    private(set) var action: String
    private(set) var createdAt: String
}
