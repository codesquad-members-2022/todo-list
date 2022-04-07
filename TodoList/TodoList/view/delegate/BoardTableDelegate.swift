//
//  BoardTableDelegate.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/07.
//

import Foundation
import UIKit

class BoardTableDelegate: NSObject{
    private(set) var cards: [TableCardUsable] = []
    func setCards(cards: [TableCardUsable]){
        self.cards = cards
    }
}
extension BoardTableDelegate: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return cards.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "CardTableViewCell") as? CardTableViewCell else{ return UITableViewCell() }
        
        
        return cell
    }
    
}
