import Foundation

class CardManager: CardManagable {
    
    private var cardListID: Int
    private var cards = [Cardable]()
    private var selectedCard: Cardable?
    private var currentlyAddedCard: Cardable?
    private var cardFactory: ModelFactoryBase
    
    var count: Int {
        return cards.count
    }
    
    subscript(_ index: Int) -> Cardable? {
        if index > count-1 {
            return nil
        }
        return cards[index]
    }
    
    init(cardListType: CardList, cardFactory: ModelFactoryBase) {
        self.cardListID = cardListType.id
        self.cardFactory = cardFactory
    }
    
    func add(title: String, body: String) {
        let data = [cardListID]
        let newCard = cardFactory.make(factoriable: Card.self, title: title, body: body, data: data)
        cards.insert(newCard, at: 0)
        
        let userInfo = [userInfoKeys.addedCard: newCard]
        NotificationCenter.default.post(name: NotificationNames.didAddNewCard, object: self, userInfo: userInfo)
        
        currentlyAddedCard = newCard
    }
    
    func add(newCard: Cardable, at index: Int) {
        cards.insert(newCard, at: index)
        
        var targetCardId = -1
        
        if let targetCard = self[index + 1] as? Card {
            targetCardId = targetCard.id
        }
        
        let userInfo: [String: Any] = [userInfoKeys.movedCard: newCard,
                                       userInfoKeys.targetCardId: targetCardId]
        NotificationCenter.default.post(name: NotificationNames.didAddNewCardFromOtherCardList, object: self, userInfo: userInfo)
    }
    
    func remove(at index: Int, isMovingState: Bool) {
        let removedCard = cards.remove(at: index)
        
        if !isMovingState {
            let userInfo: [String: Any] = [userInfoKeys.removedCardIndex: index,
                                           userInfoKeys.removedCard: removedCard]
            NotificationCenter.default.post(name: NotificationNames.didRemoveCard, object: self, userInfo: userInfo)
        }
    }
    
    func setNewCardsID(with id: Int) {
        guard currentlyAddedCard != nil else {return}
        
        currentlyAddedCard?.setID(with: id)
    }
    
    func selectCard(index: Int) -> Cardable {
        self.selectedCard = cards[index]
        return cards[index]
    }
}

extension CardManager {
    enum NotificationNames {
        static let didAddNewCard = Notification.Name("CardManagerDidAddNewCard")
        static let didRemoveCard = Notification.Name("CardManagerDidRemoveCard")
        static let didAddNewCardFromOtherCardList = Notification.Name("CardManagerDidAddNewCardFromOtherCardList")
    }
    
    enum userInfoKeys {
        static let addedCard = "addedCard"
        static let movedCard = "movedCard"
        static let targetCardId = "targetCardId"
        static let removedCard = "removedCard"
        static let removedCardIndex = "removedCardIndex"
    }
}
