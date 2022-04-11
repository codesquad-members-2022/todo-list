//
//  CardTableDataSource.swift
//  TodoList
//
//  Created by Bibi on 2022/04/11.
//

import Foundation
import UIKit

class CardTableDataSource: NSObject {
    private var cards: [TableCardUsable] = []
    
    func appendCards(card: TableCardUsable) {
        self.cards.append(card)
    }
    
    func setCards(cards: [TableCardUsable]){
        self.cards = cards
    }
}

extension CardTableDataSource: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return cards.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CardTableViewCell.identifier) as? CardTableViewCell else { return UITableViewCell() }
        let card = cards[indexPath.row]
        cell.setCellUIData(title: card.getTitle(), content: card.getContents(), writer: "author by " + card.getWriter())
        return cell
    }
}
