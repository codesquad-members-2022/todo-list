//
//  Board.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/07.
//

import Foundation

final class Board{
    private(set) var todoCards = [Card]()
    private(set) var doingCards = [Card]()
    private(set) var doneCards = [Card]()
    
    func divideCard(){
        guard let data = URLManager.requestGet(url: "/api/cards") else { return }
        
        if let cards: [Card] = JsonConverter.decodeJson(data: data){
            todoCards.append(contentsOf: cards.filter{ $0.section == .todo })
            doingCards.append(contentsOf: cards.filter{ $0.section == .doing })
            doneCards.append(contentsOf: cards.filter{ $0.section == .done })
        } else{
            return
        }
    }
}
