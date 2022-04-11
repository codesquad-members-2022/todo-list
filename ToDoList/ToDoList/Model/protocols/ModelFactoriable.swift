import Foundation

protocol ModelFactoriable {
    static func make(title: String, body: String, data: [Any]) -> Cardable
}
