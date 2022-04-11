import Foundation

class CardManager {
    private var listName: String
    private var cards = [Cardable]()
    private var selectedCard: Cardable?
    
    var count: Int {
        return cards.count
    }
    
    subscript(_ index: Int) -> Cardable {
        return cards[index]
    }
    
    init(listName: String) {
        self.listName = listName
    }
    
    func add() {
        let newCard = CardFactory.makeRandomCard(in: self.listName)
        cards.append(newCard)
    }
    
    func selectCard(index: Int) {
        self.selectedCard = cards[index]
    }
}
