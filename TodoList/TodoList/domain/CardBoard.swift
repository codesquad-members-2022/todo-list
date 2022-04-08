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
    
    private(set) var todoCards: [TableCardUsable] = []
    private(set) var doingCards: [TableCardUsable] = []
    private(set) var doneCards: [TableCardUsable] = []
    
    private let cardFactory = CardFactory()
    
    func addCard(title: String, content: String) {
        let card = cardFactory.createTableCard(title: title, contents: content)
        todoCards.insert(card, at: 0)
    }
    
    
    
}
