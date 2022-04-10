//
//  MockDatabase.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/07.
//

import Foundation

class MockDataBase {
    
    private var cards: [MockCard]
    
    init() {
        var cardCounting = MockColumnType.allCases.reduce(into: [MockColumnType:Int]()) { $0[$1] = 0 }
        
        cards = (0..<100).compactMap { index -> MockCard? in
            guard let randomColumn = MockColumnType.allCases.randomElement(),
                  let cardCount = cardCounting[randomColumn] else {
                return nil
            }
            let newCard = MockCard(id: index, title: "타이틀 \(index)", content: "내용 \(index)", author_system: "author by iOS",                column_name: randomColumn,order_id: cardCount)
            
            cardCounting[randomColumn] = cardCount + 1
            return newCard
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
        
        if Int(lastPath) != nil && urlRequest.httpMethod == "PUT" {
            return editCard(urlRequest)
        }
        
        if urlRequest.httpMethod == "PUT" {
            return deleteCard(urlRequest)
        }
        
        return (nil, nil)
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
            return ClientColumn(type: type, cards: cards)
        }
        
        guard let data = try? JSONEncoder().encode(columns) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }

        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
    
    private func addCard(_ urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let addCardData = bodyParser(AddCard.self, urlRequest: urlRequest) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        let newCard = MockCard(id: cards.count, title: addCardData.title, content: addCardData.content, author_system: "author by iOS", column_name: addCardData.column, order_id: 0)
        
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
        
        let toColumnCards = getCards(column: moveCardData.column).filter{ $0.order_id >= moveCardData.index}
        refreshOrderId(cards: toColumnCards, moveValue: 1)
        
        card.changeColumn(moveCardData.column)
        card.changeOrderId(moveCardData.index)
        
        return (Data(), HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
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
    
//    private class Column {
//        let columnType: MockColumnType
//        public private(set) var cardIds: [Int] = []
//
//        init(type: MockColumnType) {
//            self.columnType = type
//        }
//
//        func appendCardId(_ id: Int) {
//            cardIds.append(id)
//        }
//
//        func insertCardId(_ id: Int, at: Int) {
//            cardIds.insert(id, at: at)
//        }
//
//        func removeId(_ id: Int) {
//            guard let cardIndex = cardIds.firstIndex(of: id) else {
//                return
//            }
//
//            cardIds.remove(at: cardIndex)
//        }
//    }
//
//    private let columnTables: [MockColumnType: Column]
//    private var cardTables: [Int:MockCard]
//
//    init() {
//        let tables:[MockColumnType:Column] = [
//            .todo:Column(type: .todo),
//            .progress:Column(type: .progress),
//            .done:Column(type: .done)
//        ]
//
//        let columns: [MockColumnType] = [.todo, .progress, .done]
//
//        var cards = [Int:MockCard]()
//        (0..<100).forEach { index in
//            let columnIndex = Int.random(in: 0..<3)
//            cards[index] = MockCard(id: index, title: "타이틀\(index)", body: "내용\(index)", caption: "author by iOS", column: columns[columnIndex])
//            tables[columns[columnIndex]]?.appendCardId(index)
//        }
//        self.cardTables = cards
//        self.columnTables = tables
//    }
//
//    func databaseProcess(urlRequest: URLRequest, isRequestSuccess: Bool) -> (Data?, HTTPURLResponse?) {
//        guard let url = urlRequest.url else {
//            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
//        }
//
//        if isRequestSuccess == false {
//            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
//        }
//
//        let api = url.lastPathComponent
//
//        switch api {
//        case "loadColumns":
//            return loadColumn(urlRequest: urlRequest)
////        case "loadColumns":
////            return loadColumn(urlRequest: urlRequest)
////        case "moveCard":
////            return moveCard(urlRequest: urlRequest)
////        case "deleteCard":
////            return deleteCard(urlRequest: urlRequest)
////        case "addCard":
////            return addCard(urlRequest: urlRequest)
////        case "editCard":
////            return editCard(urlRequest: urlRequest)
//        default:
//            break
//        }
//        return (nil, HTTPURLResponse(url: url, statusCode: 402, httpVersion: "2", headerFields: nil))
//    }
    
    
    
//    private func loadColumn(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
//        guard let data = urlRequest.httpBody,
//              let loadColumn = try? JSONDecoder().decode(LoadColumn.self, from: data),
//              let columnTable = self.columnTables[loadColumn.column] else {
//            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
//        }
//
//        let cardIds = columnTable.cardIds
//        let cards: [ClientCard] = cardIds.compactMap{
//            guard let mockCard = self.cardTables[$0] else {
//                return nil
//            }
//            return ClientCard(id: mockCard.id, title: mockCard.title, content: mockCard.body, author_system: mockCard.caption)
//        }
//        guard let data = try? JSONEncoder().encode(cards) else {
//            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
//        }
//        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
//    }
//
//    private func addCard(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
//        guard let data = urlRequest.httpBody,
//              let addCard = try? JSONDecoder().decode(AddCard.self, from: data),
//              let columnTable = self.columnTables[addCard.column] else {
//            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
//        }
//
//        let cardId = self.cardTables.count
//        let newCard = MockCard(id: cardId, title: addCard.title, body: addCard.content, caption: "author by iOS", column: addCard.column)
//
//        columnTable.insertCardId(cardId, at: 0)
//        self.cardTables[cardId] = newCard
//
//        let clientCard = ClientCard(id: cardId, title: newCard.title, content: newCard.body, author_system: newCard.caption)
//
//        guard let data = try? JSONEncoder().encode(clientCard) else {
//            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
//        }
//
//        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
//    }
//
//    private func moveCard(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
//        guard let data = urlRequest.httpBody,
//              let moveCard = try? JSONDecoder().decode(MoveCard.self, from: data),
//              let fromColumn = self.columnTables[moveCard.from_column],
//              let toColumn = self.columnTables[moveCard.to_column] else {
//            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
//        }
//
//        fromColumn.removeId(moveCard.id)
//        toColumn.insertCardId(moveCard.id, at: 0)
//        return (Data(), HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
//    }
//
//    private func editCard(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
//        guard let data = urlRequest.httpBody,
//              let editCard = try? JSONDecoder().decode(EditCard.self, from: data),
//              let oldCard = self.cardTables[editCard.id] else {
//            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
//        }
//
//        let newCard = MockCard(id: oldCard.id, title: editCard.title, body: editCard.content, caption: oldCard.caption, column: oldCard.column)
//        self.cardTables[newCard.id] = newCard
//
//        let clientCard = ClientCard(id: newCard.id, title: newCard.title, content: newCard.body, author_system: newCard.caption)
//
//        guard let data = try? JSONEncoder().encode(clientCard) else {
//            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
//        }
//
//        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
//    }
//
//    private func deleteCard(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
//        guard let data = urlRequest.httpBody,
//              let deleteCard = try? JSONDecoder().decode(DeleteCard.self, from: data),
//              let card = self.cardTables.removeValue(forKey: deleteCard.id),
//              let column = self.columnTables[card.column] else {
//            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
//        }
//
//        column.removeId(card.id)
//        self.cardTables.removeValue(forKey: card.id)
//        return (Data(), HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
//    }
}

//extension MockDataBase {
//    //Server Model
//    struct MockCard: Decodable {
//        let id: Int
//        let title: String
//        let body: String
//        let caption: String
//        let column: MockColumnType
//    }
//
//    enum MockColumnType: String, Decodable {
//        case todo = "to_do"
//        case progress = "in_progress"
//        case done = "done"
//    }
//}
//
//extension MockDataBase {
//    //client Model
//    struct ClientCard: Encodable {
//        let id: Int
//        let title: String
//        let content: String
//        let author_system: String
//    }
//
//    struct LoadColumn: Decodable {
//        let column: MockColumnType
//    }
//
//    struct AddCard: Decodable {
//        let title: String
//        let content: String
//        let column: MockColumnType
//    }
//
//    struct EditCard: Decodable {
//        let id: Int
//        let title: String
//        let content: String
//    }
//
//    struct MoveCard: Decodable {
//        let id: Int
//        let from_column: MockColumnType
//        let to_column: MockColumnType
//    }
//
//    struct DeleteCard: Decodable {
//        let id: Int
//    }
//}
