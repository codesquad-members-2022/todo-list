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
    
    static let shared = Board()
    
    private init(){ }
    
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
    
    func postCard(card: Card, section: BoardSubscriptIndex){
        var newCard = card
        let postCard = PostCard(section: card.section.rawValue, title: card.title, content: card.content, userID: "chez")
        guard let encodingData: Data = JsonConverter.encodeJson(param: postCard) else { return }
        
        URLManager.requestPost(url: "http://3.39.150.251:8080/api/cards", encodingData: encodingData){ data in
            do{
                let subData = try JSONDecoder().decode(PostResponse.self, from: data)
                
                newCard.changeId(id: subData.id)
                newCard.changeDate(date: subData.modifiedAt)
                
                self.addCard(newCard, at: section)
            } catch{
                return
            }
            //guard let subData: [PostResponse] = JsonConverter.decodeJson(data: data) else { return }
            //guard let responseData = subData.first else { return }
            
            
        }
    }
    
    func addCard(_ card: Card, at section: BoardSubscriptIndex){
        NotificationCenter.default.post(name: NSNotification.Name(rawValue: "addCard"), object: self)
        
        switch section {
        case .todo:
            todoCards.insert(card, at: 0)
        case .doing:
            doingCards.insert(card, at: 0)
        case .done:
            doneCards.insert(card, at: 0)
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
