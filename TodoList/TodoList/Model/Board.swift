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
    
    subscript(index: BoardSubscriptIndex) -> [Card]{
        switch index {
        case .none:
            return [Card]()
        case .todo:
            return todoCards
        case .doing:
            return doingCards
        case .done:
            return doneCards
        }
    }
    
    func getAndDivideCard(){
        URLManager.requestGet(url: "http://3.39.150.251:8080/api/cards?userId=chez"){ data in
            guard let cards: [Card] = JsonConverter.decodeJson(data: data) else { return }
            
            self.todoCards.append(contentsOf: cards.filter{ $0.section == .todo })
            self.doingCards.append(contentsOf: cards.filter{ $0.section == .doing })
            self.doneCards.append(contentsOf: cards.filter{ $0.section == .done })
            
            NotificationCenter.default.post(name: NSNotification.Name(rawValue: "board"), object: self)
        }
    }
    
    func postCard(card: Card){
        var newCard = card
        guard let encodingData: Data = JsonConverter.encodeJson(param: newCard) else { return }
        
        URLManager.requestPost(url: "http://3.39.150.251:8080/api/cards?userId=chez", encodingData: encodingData){ data in
            guard let subData: [String] = JsonConverter.decodeJson(data: data) else { return }
            
            guard let id = Int(subData[0]) else{ return }
            newCard.changeId(id: id)
            newCard.changeDate(date: subData[1])
        }
    }
    
    func addCard(_ card: Card, at section: BoardSubscriptIndex){
        switch section {
        case .todo:
            todoCards.append(card)
        case .doing:
            doingCards.append(card)
        case .done:
            doneCards.append(card)
        case .none:
            return
        }
    }
}

private extension Board{
    func dateFormatter(date: String) -> Date?{
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
        dateFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone?
        
        let date = dateFormatter.date(from: date)
        return date
    }
}
