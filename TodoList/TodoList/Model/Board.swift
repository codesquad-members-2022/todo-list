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
    
    subscript(index: Int) -> [Card]{
        switch index{
        case 0:
            return todoCards
        case 1:
            return doingCards
        case 2:
            return doneCards
        default:
            return [Card]()
        }
    }
    
    init(){
        self.getAnddivideCard()
    }
    
    func postCard(section: Int, title: String, content: String, userID: String){
        var newCard = Card(section: section, title: title, content: content, userID: userID)
        let data = JsonConverter.encodeJson(param: newCard)
        
        let responseData = URLManager.requestPost(url: " ", requestParam: data)
        
        guard let responseData = responseData else { return }
        let idAndDate: [String] = JsonConverter.decodeJson(data: responseData)
        
        guard let id = Int(idAndDate[0]), let date = dateFormatter(date: idAndDate[1]) else{ return }
        
        newCard.changeId(id: id)
        newCard.changeDate(date: date)
    }
}

private extension Board{
    func getAnddivideCard(){
        guard let data = URLManager.requestGet(url: "http://3.39.150.251:8080/api/cards?userId=chez") else { return }
        let cards: [Card] = JsonConverter.decodeJson(data: data)
        
        self.todoCards.append(contentsOf: cards.filter{ $0.section == .todo })
        self.doingCards.append(contentsOf: cards.filter{ $0.section == .doing })
        self.doneCards.append(contentsOf: cards.filter{ $0.section == .done })
    }
    
    func dateFormatter(date: String) -> Date?{
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
        dateFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone?
        
        let date = dateFormatter.date(from: date)
        return date
    }
}
