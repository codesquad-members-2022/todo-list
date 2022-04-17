import Foundation

protocol RequestEntityConvertible: AnyObject {
    associatedtype EntityConvertibleTarget
    func toRequestEntity() -> EntityConvertibleTarget
}
