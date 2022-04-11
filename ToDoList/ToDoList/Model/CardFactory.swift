import Foundation

struct ModelFactory: ModelFactoryBase {
    func make(factoriable: ModelFactoriable.Type, title: String, body: String, data: [Any]) -> Cardable {
        
        return factoriable.make(title: title, body: body, data: data)
    }
}
