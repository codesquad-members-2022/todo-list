import Foundation

protocol CardManagable {
    var count: Int {get}
    subscript(_ index: Int) -> Cardable? {get}
    func add(title: String, body: String)
    func add(newCard: Cardable, at index: Int)
    func remove(at index: Int, isMovingState: Bool)
    func setNewCardsID(with id: Int)
    func selectCard(index: Int) -> Cardable
}
