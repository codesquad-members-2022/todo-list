import Foundation

struct MemoPostResponse: Codable {
    let idx: Int
    let title: String
    let content: String
    let author: String
    let status: Int
    let createAt: String
}

typealias MemoPutResponse = MemoPostResponse
typealias MemoGetResponse = MemoPostResponse

struct MemoDeleteResponse: Codable, CommonResponseType {
    let code: Int
    let message: String?
    let id: String
}
