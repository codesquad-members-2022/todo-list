import Foundation

protocol CommonResponseType {
    associatedtype DtoConvertibleTarget
    func toResponseDto() -> DtoConvertibleTarget
//    var code: Int { get }
//    var message: String? { get }
//    var id: String { get }
}
