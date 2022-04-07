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
    
    private(set) var todoCards: [CardUsable] = []
    private(set) var doingCards: [CardUsable] = []
    private(set) var doneCards: [CardUsable] = []
    
    private let cardFactory = CardFactory()
    
    func addCard(title: String, content: String) {
        let card = cardFactory.createCard(title: title, contents: content)
        todoCards.insert(card, at: 0)
    }
}
