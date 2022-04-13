import Foundation

struct TaskCard: Codable {
    var id : Int
    var section : String
    var title : String
    var content : String
}


struct RequestCardData: Codable {
    var section : String
    var title : String
    var content : String
}


