//
//  CardFactory.swift
//  TodoList
//
//  Created by Bibi on 2022/04/05.
//

import Foundation

struct CardFactory {
    
    private let writers = ["ebony", "bibi", "JK", "sol", "mase", "rosa", "jee"]
    private let writerEbony = "ebony"
    
    func createCard(title: String, contents: String) -> CardUsable {
        return Card(status: CardStatus.todo, title: title, contents: contents, writer: writerEbony)
    }
    
    func createRandomCard() -> CardUsable {
        let randomWriter = writers[Int.random(in: writers.indices)]
        
        return Card(status: CardStatus.todo, title: String(UUID().uuidString.prefix(8)), contents: String(UUID().uuidString.prefix(8)), writer: randomWriter)
    }
}
