import Foundation

struct HistoryEntity: Codable, Identifiable {
    typealias Identifier = String
    
    let id: Identifier
    let author: String
    let content: String
    let time: String
}


struct HistoryList: Codable {
    let historyList: [HistoryEntity]
    let count: Int
}
