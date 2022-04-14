import Foundation

protocol CommonResponseType {
    var code: Int { get }
    var message: String? { get }
    var id: String { get }
}
