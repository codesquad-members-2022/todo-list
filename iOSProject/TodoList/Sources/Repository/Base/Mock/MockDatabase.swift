//
//  MockDatabase.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/07.
//

import Foundation

class MockDataBase {
    private class Column {
        let columnType: MockColumnType
        public private(set) var cardIds: [Int] = []
        
        init(type: MockColumnType) {
            self.columnType = type
        }
        
        func appendCardId(_ id: Int) {
            cardIds.append(id)
        }
        
        func insertCardId(_ id: Int, at: Int) {
            cardIds.insert(id, at: at)
        }
        
        func removeId(_ id: Int) {
            guard let cardIndex = cardIds.firstIndex(of: id) else {
                return
            }
            
            cardIds.remove(at: cardIndex)
        }
    }
    
    private let columnTables: [MockColumnType: Column]
    private var cardTables: [Int:MockCard]
    
    init() {
        let tables:[MockColumnType:Column] = [
            .todo:Column(type: .todo),
            .progress:Column(type: .progress),
            .done:Column(type: .done)
        ]
        
        let columns: [MockColumnType] = [.todo, .progress, .done]
        
        var cards = [Int:MockCard]()
        (0..<100).forEach { index in
            let columnIndex = Int.random(in: 0..<3)
            cards[index] = MockCard(id: index, title: "타이틀\(index)", body: "내용\(index)", caption: "author by iOS", column: columns[columnIndex])
            tables[columns[columnIndex]]?.appendCardId(index)
        }
        self.cardTables = cards
        self.columnTables = tables
    }
    
    func databaseProcess(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let url = urlRequest.url else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        let api = url.lastPathComponent
        
        switch api {
        case "loadColumn":
            return loadColumn(urlRequest: urlRequest)
        case "moveCard":
            return moveCard(urlRequest: urlRequest)
        case "deleteCard":
            return deleteCard(urlRequest: urlRequest)
        case "addCard":
            return addCard(urlRequest: urlRequest)
        case "editCard":
            return editCard(urlRequest: urlRequest)
        default:
            break
        }
        return (nil, HTTPURLResponse(url: url, statusCode: 402, httpVersion: "2", headerFields: nil))
    }
    
    private func loadColumn(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let data = urlRequest.httpBody,
              let loadColumn = try? JSONDecoder().decode(LoadColumn.self, from: data),
              let columnTable = self.columnTables[loadColumn.column] else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        let cardIds = columnTable.cardIds
        let cards: [ClientCard] = cardIds.compactMap{
            guard let mockCard = self.cardTables[$0] else {
                return nil
            }
            return ClientCard(id: mockCard.id, title: mockCard.title, content: mockCard.body, author_system: mockCard.caption)
        }
        guard let data = try? JSONEncoder().encode(cards) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
    
    private func addCard(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let data = urlRequest.httpBody,
              let addCard = try? JSONDecoder().decode(AddCard.self, from: data),
              let columnTable = self.columnTables[addCard.column] else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }

        let cardId = self.cardTables.count
        let newCard = MockCard(id: cardId, title: addCard.title, body: addCard.content, caption: "author by iOS", column: addCard.column)
        
        columnTable.insertCardId(cardId, at: 0)
        self.cardTables[cardId] = newCard
        
        let clientCard = ClientCard(id: cardId, title: newCard.title, content: newCard.body, author_system: newCard.caption)
        
        guard let data = try? JSONEncoder().encode(clientCard) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
    
    private func moveCard(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let data = urlRequest.httpBody,
              let moveCard = try? JSONDecoder().decode(MoveCard.self, from: data),
              let fromColumn = self.columnTables[moveCard.from_column],
              let toColumn = self.columnTables[moveCard.to_column] else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        fromColumn.removeId(moveCard.id)
        toColumn.insertCardId(moveCard.id, at: 0)
        return (Data(), HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
    
    private func editCard(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let data = urlRequest.httpBody,
              let editCard = try? JSONDecoder().decode(EditCard.self, from: data),
              let oldCard = self.cardTables[editCard.id] else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        let newCard = MockCard(id: oldCard.id, title: editCard.title, body: editCard.content, caption: oldCard.caption, column: oldCard.column)
        self.cardTables[newCard.id] = newCard
        
        let clientCard = ClientCard(id: newCard.id, title: newCard.title, content: newCard.body, author_system: newCard.caption)
        
        guard let data = try? JSONEncoder().encode(clientCard) else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        return (data, HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
    
    private func deleteCard(urlRequest: URLRequest) -> (Data?, HTTPURLResponse?) {
        guard let data = urlRequest.httpBody,
              let deleteCard = try? JSONDecoder().decode(DeleteCard.self, from: data),
              let card = self.cardTables.removeValue(forKey: deleteCard.id),
              let column = self.columnTables[card.column] else {
            return (nil, HTTPURLResponse(url: urlRequest.url!, statusCode: 402, httpVersion: "2", headerFields: nil))
        }
        
        column.removeId(card.id)
        self.cardTables.removeValue(forKey: card.id)
        return (Data(), HTTPURLResponse(url: urlRequest.url!, statusCode: 200, httpVersion: "2", headerFields: nil))
    }
}

extension MockDataBase {
    //Server Model
    struct MockCard: Decodable {
        let id: Int
        let title: String
        let body: String
        let caption: String
        let column: MockColumnType
    }
    
    enum MockColumnType: String, Decodable {
        case todo = "to_do"
        case progress = "in_progress"
        case done = "done"
    }
}

extension MockDataBase {
    //client Model
    struct ClientCard: Encodable {
        let id: Int
        let title: String
        let content: String
        let author_system: String
    }
    
    struct LoadColumn: Decodable {
        let column: MockColumnType
    }
    
    struct AddCard: Decodable {
        let title: String
        let content: String
        let column: MockColumnType
    }
    
    struct EditCard: Decodable {
        let id: Int
        let title: String
        let content: String
    }
    
    struct MoveCard: Decodable {
        let id: Int
        let from_column: MockColumnType
        let to_column: MockColumnType
    }
    
    struct DeleteCard: Decodable {
        let id: Int
    }
}
