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
        self.backgroundColor = ColorMaker.lightGray0.getRawValue()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.backgroundColor = ColorMaker.lightGray0.getRawValue()
    }
    
    func setTableViewId(number: Int){
        self.tableViewId = number
    }
}
