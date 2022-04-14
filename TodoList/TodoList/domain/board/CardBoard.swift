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
    private(set) var cards: [CardUsable] = []
    private let cardFactory = CardFactory()
    
    func addCard(title: String, content: String) {
        let card = cardFactory.createCard(title: title, contents: content)
        cards.insert(card, at: 0)
    }
    
    func todoTableCards() -> [TableCardUsable] {
        return cards.filter { $0.getStatus() == .todo }
    }
    
    func doingTableCards() -> [TableCardUsable] {
        return cards.filter { $0.getStatus() == .doing }
    }
    
    func doneTableCards() -> [TableCardUsable] {
        return cards.filter { $0.getStatus() == .done }
    }
    
}
