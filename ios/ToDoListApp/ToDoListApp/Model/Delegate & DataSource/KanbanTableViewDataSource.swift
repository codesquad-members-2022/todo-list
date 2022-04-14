//
//  KanbanTableViewDataSource.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/05.
//

import UIKit

class KanbanTableViewDataSource: NSObject, UITableViewDataSource {
    
    var cellData = KanbanTableCellData.dataList
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return cellData.count
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: KanbanTableViewCell.indentifier, for: indexPath) as? KanbanTableViewCell else {
            return UITableViewCell()
        }
        
        let target = cellData[indexPath.section]
        cell.changeTitleLabel(text: target.title)
        cell.changeContentLabel(text: target.content)
        return cell
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            tableView.beginUpdates()
            cellData.remove(at: indexPath.section)
            tableView.deleteSections(IndexSet(arrayLiteral: indexPath.section), with: .fade)
            tableView.endUpdates()
        }
    }
}
