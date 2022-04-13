import Foundation

struct MemoPostResponse: Codable, CommonResponseType {
    let code: Int
    let message: String?
    let id: String
    let title: String
    let content: String
    let author: String
}

typealias MemoPutResponse = MemoPostResponse
typealias MemoGetResponse = MemoPostResponse

struct MemoDeleteResponse: Codable, CommonResponseType {
    let code: Int
    let message: String?
    let id: String
}
