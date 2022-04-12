//
//  HistoryTableDelegate.swift
//  TodoList
//
//  Created by Bibi on 2022/04/12.
//

import Foundation
import UIKit

class HistoryTableDelegate: NSObject {
    
}

extension HistoryTableDelegate: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return CGFloat(200)
    }
}
