import Foundation

protocol CardManagable {
    var count: Int {get}
    subscript(_ index: Int) -> Cardable {get}
    func add(title: String, body: String)
    func add(newCard: Cardable)
    func setNewCardsID(with id: Int)
}
