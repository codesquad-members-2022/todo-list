import Foundation

protocol NetworkTargetable {
    var path: String {get}
    var parameter: [String: Any]? {get}
    var method: String {get}
}

extension NetworkTargetable {
    var url: URL {
        URL(string: "http://worldclockapi.com/api/json/est/now")!
    }
}
