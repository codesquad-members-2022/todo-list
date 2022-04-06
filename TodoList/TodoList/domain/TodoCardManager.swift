//
//  TodoCardManager.swift
//  TodoList
//
//  Created by Bibi on 2022/04/05.
//

import Foundation

class TodoCardManager {
    private(set) lazy var todoCards: [Card] = []
    
    func insertCard(_ card: Card, to index: Int) {
        todoCards.insert(card, at: index)
    }
    
    func deleteCard(at index: Int) {
        todoCards.remove(at: index)
    }
}

extension TodoCardManager: TodoCardDelegate {
    func moveCardToDoing() {
        
    }
    
    func moveCardToDone() {
        
    }
    
    
}
