import Foundation

struct TaskBoard: Codable {
    var lists : [String:TaskCardList]
}
