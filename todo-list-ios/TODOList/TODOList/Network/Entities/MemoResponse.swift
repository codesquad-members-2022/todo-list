import Foundation

struct MemoPostResponse: Decodable, CommonResponseType {
    //    Post 응답 JSON 예시
    //    {
    //        "idx": 61,
    //        "title": "제목 예시",
    //        "content": "내용 예시",
    //        "author": "sampleN",
    //        "status": 1,
    //        "createAt": "2022-04-15T15:23:55.754225"
    //    }
    
    private let idx: Int
    private let title: String
    private let content: String
    private let author: String
    private let status: Int
    private let createAt: String
    
    func toResponseDto() -> Memo {
        return Memo(title: title, content: content, name: author, status: .todo)
    }
}

//typealias MemoPutResponse = MemoPostResponse
//typealias MemoGetResponse = MemoPostResponse

//struct MemoDeleteResponse: Codable, CommonResponseType {
//    let code: Int
//    let message: String?
//    let id: String
//}
