//
//  CardFactory.swift
//  TodoList
//
//  Created by Bibi on 2022/04/05.
//

import Foundation

struct CardFactory {
    
    private let writers = ["ebony", "bibi", "JK", "sol", "mase", "rosa", "jee"]
    
    func createTableCard(title: String, contents: String) -> TableCardUsable {
        return Card(status: CardStatus.todo, title: title, contents: contents, writer: "ebony")
    }
    
    func createRandomTableCard() -> TableCardUsable {
        let randomWriter = writers[Int.random(in: writers.indices)]
        
        return Card(status: CardStatus.todo, title: "title " + String(UUID().uuidString.prefix(8)), contents: "contents " + String(UUID().uuidString.prefix(8)), writer: randomWriter)
    }
}
