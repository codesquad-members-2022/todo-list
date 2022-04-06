//
//  TaskTableViewCell.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/05.
//

import UIKit

class TaskTableViewCell: UITableViewCell {
    static let identifier = "taskTableViewCell"
    static let titleFontString = "NotoSansKR-Bold"
    static let contentsFontString = "NotoSansKR-Regular"
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
    }
    
}
