//
//  CardBoard.swift
//  TodoList
//
//  Created by Bibi on 2022/04/05.
//

import Foundation

class CardBoard {
    private(set) var selectedCard: Card?
    private(set) var selectedIndex: Int?
    private(set) var todoCards: [Card] = []
    private(set) var doingCards: [Card] = []
    private(set) var doneCards: [Card] = []
    private let cardFactory = CardFactory()
    
    func addNewCard(){
        guard let card = cardFactory.createRandomCard() as? Card else { return }
        let data = try? JSONEncoder().encode(card)
        
    }
}
