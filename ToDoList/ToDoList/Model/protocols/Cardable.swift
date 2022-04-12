import Foundation

protocol Cardable {
    var title: String {get}
    var body: String {get}
    mutating func moveList(to newListName: String)
    mutating func setIndexOrder(in cardsCount: Int)
    mutating func setID(with id: Int)
}
