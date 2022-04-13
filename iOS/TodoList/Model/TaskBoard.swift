import Foundation

struct TaskBoard: Codable {
    var cards: [String:[TaskCard]]
}
