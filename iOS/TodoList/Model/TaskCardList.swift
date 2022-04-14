import Foundation

struct TaskCardList {
    private var sectionTitle: String
    private var cards: [TaskCard]
    
    init(sectionTitle: String, cards: [TaskCard]) {
        self.sectionTitle = sectionTitle
        self.cards = cards
    }
    
    var count: Int {
        return self.cards.count
    }
    
    func getCard(at index: Int) -> TaskCard {
        return self.cards[index]
    }
}
