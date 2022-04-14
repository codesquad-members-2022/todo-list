import Foundation

struct TaskCard: Codable {
    private(set) var id : Int
    private(set) var section : String
    private(set) var title : String
    private(set) var content : String
}


struct RequestCardData: Codable {
    private(set) var section : String
    private(set) var title : String
    private(set) var content : String
}


