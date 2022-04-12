import Foundation

class CardManager: CardManagable {
    
    enum Constants {
        enum NotificationNames {
            static let didAddNewCard = Notification.Name("CardManagerDidAddNewCard")
            static let didRemoveCard = Notification.Name("CardManagerDidRemoveCard")
        }
        
        enum userInfoKeys {
            static let addedCard = "addedCard"
            static let removedCard = "removedCard"
            static let removedCardIndex = "removedCardIndex"
        }
    }
    
    private var cardListID: Int
    private var cards = [Cardable]()
    private var selectedCard: Cardable?
    private var currentlyAddedCard: Cardable?
    private var cardFactory: ModelFactoryBase
    
    var count: Int {
        return cards.count
    }
    
    subscript(_ index: Int) -> Cardable {
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
        
        let userInfo = [Constants.userInfoKeys.addedCard: newCard]
        NotificationCenter.default.post(name: Constants.NotificationNames.didAddNewCard, object: self, userInfo: userInfo)
        
        currentlyAddedCard = newCard
    }
    
    func add(newCard: Cardable) {
        cards.append(newCard)
        
        let userInfo = [Constants.userInfoKeys.addedCard: newCard]
        NotificationCenter.default.post(name: Constants.NotificationNames.didAddNewCard, object: self, userInfo: userInfo)
    }
    
    func remove(at index: Int) {
        let removedCard = cards.remove(at: index)
        
        let userInfo: [String: Any] = [Constants.userInfoKeys.removedCardIndex: index,
                        Constants.userInfoKeys.removedCard: removedCard]
        NotificationCenter.default.post(name: Constants.NotificationNames.didRemoveCard, object: self, userInfo: userInfo)
    }
    
    func setNewCardsID(with id: Int) {
        guard currentlyAddedCard != nil else {return}
        
        currentlyAddedCard?.setID(with: id)
    }
    
    func selectCard(index: Int) {
        self.selectedCard = cards[index]
    }
}
