import Foundation

struct MemoEntity: Codable, Identifiable {
    typealias Identifier = String
    
    let id: Identifier
    let title: String
    let content: String
    let author: String
    let status: MemoStatus
}


struct MemoList: Codable {
    let memoList: [MemoEntity]
    let count: Int
}
