import Foundation

enum API {
    static let baseURL: String = "http://ec2-54-253-23-157.ap-southeast-2.compute.amazonaws.com/"
    static let postURL: String = baseURL + "todo"
    static let getTodosURL: String = baseURL + "todos"
    static let getEventURL: String = baseURL + "events"
    static var putURL: (String) -> String = { id in baseURL + "todo/\(id)" }
    static let deleteURL: (String) -> String = { id in baseURL + "todo/\(id)" }
}
