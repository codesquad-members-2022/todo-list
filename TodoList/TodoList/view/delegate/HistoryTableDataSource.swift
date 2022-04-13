//
//  HistoryTableDatasource.swift
//  TodoList
//
//  Created by Bibi on 2022/04/12.
//

import Foundation
import UIKit

class HistoryTableDataSource: NSObject {
    private var histories: [HistoryCard] = [HistoryCard(writer: "bibi", title: "historycardtitleTest", beforeStatus: .todo, afterStatus: .doing, action: .move, date: .now)]
}

extension HistoryTableDataSource: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: HistoryTableViewCell.identifier) as? HistoryTableViewCell else { return UITableViewCell() }
        let card = histories[indexPath.row]
        cell.setCellUIData(title: card.getTitleString(), writer: card.writer, time: card.date)
        return cell
    }
}
