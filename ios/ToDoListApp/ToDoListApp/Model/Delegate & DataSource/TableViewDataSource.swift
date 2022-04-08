//
//  TableViewDataSource.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/05.
//

import UIKit

class TableViewDataSource: NSObject, UITableViewDataSource {
    
    let cellData = CellData.dataList
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return cellData.count
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CustomTableViewCell.indentifier, for: indexPath) as? CustomTableViewCell else {
            return UITableViewCell()
        }
        
        let target = cellData[indexPath.section]
        cell.changeTitleLabel(text: target.title)
        cell.changeContentLabel(text: target.content)
        return cell
    }
}
