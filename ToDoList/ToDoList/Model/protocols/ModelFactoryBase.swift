import Foundation

protocol ModelFactoryBase {
    func make(factoriable: ModelFactoriable.Type, title: String, body: String, data: [Any]) -> Cardable
}
