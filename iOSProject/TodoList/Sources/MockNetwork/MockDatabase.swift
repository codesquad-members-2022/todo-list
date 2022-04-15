//
//  MockDatabase.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/07.
//

import Foundation

final class MockDataBase {
    
    private var cards: [MockCard]
    private var logs: [MockLog]
    
    init() {
        var cardCounting = MockColumnType.allCases.reduce(into: [MockColumnType:Int]()) { $0[$1] = 0 }
        
        cards = (0..<100).compactMap { index -> MockCard? in
            guard let randomColumn = MockColumnType.allCases.randomElement(),
                  let cardCount = cardCounting[randomColumn] else {
                return nil
            }
            let newCard = MockCard(id: index, title: "타이틀 \(index)", content: "내용 \(index)", author_system: "iOS",                column_name: randomColumn,order_id: cardCount)
            
            cardCounting[randomColumn] = cardCount + 1
            return newCard
        }
        
        logs = (0..<100).map { index in
            MockLog(author: "작성자\(index)", createdDate: Date.now.toString(format: "yyyy-MM-dd"), text: "내용물 \(index)")
        }
    }
    
    func databaseProcess(urlRequest: URLRequest, isRequestSuccess: Bool) -> (Data?, HTTPURLResponse?) {
        guard let url = urlRequest.url else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }

        if isRequestSuccess == false {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        let lastPath = url.lastPathComponent
        
        if lastPath == "cards" && urlRequest.httpMethod == "GET" {
            return loadColumn(urlRequest)
        }
        
        if lastPath == "cards" && urlRequest.httpMethod == "POST" {
            return addCard(urlRequest)
        }
        
        if lastPath == "cards" && urlRequest.httpMethod == "PUT" {
            return moveCard(urlRequest)
        }
        
        if Int(lastPath) != nil && urlRequest.httpMethod == "PUT" {
            return editCard(urlRequest)
        }
        
        if urlRequest.httpMethod == "DELETE" {
            return deleteCard(urlRequest)
        }
        
        if lastPath == "logs" {
            return logs(urlRequest)
        }
        
        return (nil, nil)
    }
    
    private func getCard(id: Int) -> MockCard? {
        cards.first(where: { $0.id == id})
    }
    
    private func getCards(column: MockColumnType) -> [MockCard] {
        cards.filter { $0.column_name == column}.sorted { $0.order_id < $1.order_id}
    }
    
    private func refreshOrderId(cards: [MockCard], moveValue: Int) {
        cards.forEach { card in
            card.changeOrderId(card.order_id + moveValue)
        }
    }
    
    private func bodyParser<T: Decodable>(_ type: T.Type, urlRequest: URLRequest) -> T? {
        guard let data = urlRequest.httpBody,
              let decodeData = try? JSONDecoder().decode(type.self, from: data) else {
                  return nil
              }
        return decodeData
    }
}

extension MockDataBase {
    private func logs(_ urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let data = try? JSONEncoder().encode(logs) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
    
    private func deleteCard(_ urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let url = urlRequest.url,
              let id = Int(url.lastPathComponent),
              let card = getCard(id: id),
              let index = cards.firstIndex(of: card) else {
                  return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
              }
        
        let columnCards = getCards(column: card.column_name).filter{ $0.order_id > card.order_id}
        refreshOrderId(cards: columnCards, moveValue: -1)
        
        cards.remove(at: index)
        return (Data(), HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
    
    private func editCard(_ urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let url = urlRequest.url,
              let id = Int(url.lastPathComponent),
              let editCardData = bodyParser(EditCard.self, urlRequest: urlRequest),
              let card = getCard(id: id) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        card.changeTitle(editCardData.title)
        card.changeContent(editCardData.content)
        
        guard let data = try? JSONEncoder().encode(card.toClientData()) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }

        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
    
    private func loadColumn(_ urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        let columns = MockColumnType.allCases.map { type -> ClientColumn in
            let cards = getCards(column: type).map { $0.toClientData()}
            return ClientColumn(columnType: type, cards: cards)
        }
        
        let clientColumn = ClientColumns(columns: columns)
        
        guard let data = try? JSONEncoder().encode(clientColumn) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }

        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
    
    private func addCard(_ urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let addCardData = bodyParser(AddCard.self, urlRequest: urlRequest) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        let newCard = MockCard(id: cards.count, title: addCardData.title, content: addCardData.content, author_system: "iOS", column_name: addCardData.column, order_id: 0)
        
        let columnCards = getCards(column: addCardData.column).filter{ $0.order_id >= 0}
        refreshOrderId(cards: columnCards, moveValue: 1)
        cards.insert(newCard, at: 0)
        
        let clientCard = newCard.toClientData()
        
        guard let data = try? JSONEncoder().encode(clientCard) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }

        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
    
    private func moveCard(_ urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let moveCardData = bodyParser(MoveCard.self, urlRequest: urlRequest),
              let card = getCard(id: moveCardData.id) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        let fromColumnCards = getCards(column: card.column_name).filter{ $0.order_id > card.order_id}
        refreshOrderId(cards: fromColumnCards, moveValue: -1)
        
        let toColumnCards = getCards(column: moveCardData.newColumnName).filter{ $0.order_id >= moveCardData.newOrderIndex}
        refreshOrderId(cards: toColumnCards, moveValue: 1)
        
        card.changeColumn(moveCardData.newColumnName)
        card.changeOrderId(moveCardData.newOrderIndex)
        
        return (Data(), HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
}
