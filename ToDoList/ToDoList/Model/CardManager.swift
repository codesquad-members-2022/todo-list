import Foundation

class CardManager: CardManagable {
    
    private var listName: String
    private var cards = [Cardable]()
    private var selectedCard: Cardable?
    private var cardFactory: ModelFactoryBase
    
    var count: Int {
        return cards.count
    }
    
    subscript(_ index: Int) -> Cardable {
        return cards[index]
    }
    
    init(listName: String, cardFactory: ModelFactoryBase) {
        self.listName = listName
        self.cardFactory = cardFactory
    }
    
    func add(title: String, body: String) {
        let data = [listName]
        let newCard = cardFactory.make(factoriable: Card.self, title: title, body: body, data: data)
        cards.append(newCard)
    }
    
    func add(newCard: Cardable) {
        cards.append(newCard)
    }
    
    func selectCard(index: Int) {
        self.selectedCard = cards[index]
    }
}
