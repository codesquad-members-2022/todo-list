//
//  BoardTableDelegate.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/07.
//

import Foundation
import UIKit

class CardTableDelegate: NSObject {

}

extension CardTableDelegate: UITableViewDelegate{
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return CGFloat(150)
    }
}
