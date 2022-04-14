import Foundation

struct TaskBoard: Codable {
    private(set) var cards: [String:[TaskCard]]
}
