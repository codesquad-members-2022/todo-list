//
//  TaskTableViewDataSource.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/07.
//

import UIKit

class TaskTableViewDataSource: NSObject, UITableViewDataSource {
    
    let cellData = TaskData.dataList
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return cellData.count
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: InspectorViewCell.indentifier, for: indexPath) as? InspectorViewCell else {
            return UITableViewCell()
        }
        
        let target = cellData[indexPath.section]
        cell.changeEmojiLabel(text: target.emoji)
        cell.changeUserLabel(text: target.user)
        cell.changeContentsLabel(text: target.content)
        cell.changeTimeLabel(text: target.time)
        return cell
    }
}
