import Foundation

struct TaskCardList {
    var sectionTitle: String
    var cards: [TaskCard]
    var count: Int {
        return self.cards.count
    }
    
    func getCard(at index: Int) -> TaskCard {
        return self.cards[index]
    }
}
