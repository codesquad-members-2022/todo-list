//
//  TodoTableView.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/05.
//

import UIKit

final class TodoTableView: UITableView {
    private(set) var tableViewId: Int?
    
    override init(frame: CGRect, style: UITableView.Style) {
        super.init(frame: frame, style: style)
        self.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1)
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1)
    }
    
    func setTableViewId(number: Int){
        self.tableViewId = number
    }
}
