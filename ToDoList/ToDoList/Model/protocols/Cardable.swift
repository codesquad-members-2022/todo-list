import Foundation

protocol Cardable {
    var title: String {get}
    var body: String {get}
    mutating func moveList(to newCardListID: Int)
    mutating func setID(with id: Int)
}
