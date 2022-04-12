import Foundation


struct TaskCardList: Codable {
    var cards: [TaskCard] = []
    
    func getCount() -> Int{
        return self.cards.count
    }
    
    func getCard(at index: Int) -> TaskCard {
        return self.cards[index]
    }
}
