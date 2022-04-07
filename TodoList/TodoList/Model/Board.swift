//
//  Board.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/07.
//

import Foundation

class Board{
    private(set) var todoCards: [Card]?
    private(set) var doginCards: [Card]?
    private(set) var doneCards: [Card]?
    
    func divideCard() {
        guard let cards = URLManager.requestGet(url: "") else { return }
        todoCards = cards.filter{ $0.section == Card.State.todo }
        doginCards = cards.filter{ $0.section == Card.State.doing }
        doneCards = cards.filter{ $0.section == Card.State.done }
    }
}
