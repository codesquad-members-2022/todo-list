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
    
    var titleString: String = "Task 1"
    var contentsString: String = "Hello"
    var authorString: String = "Authored by iOS"
    
    let taskView = UIView()
    let titleLabel: UILabel = {
        let title = UILabel()
        
        if let notoSansKRBold = UIFont(name: titleFontString, size: 16) {
            title.font = notoSansKRBold
        }
        
        return title
    }()
    
    let contentsLabel = UITextView()
    let authoredByLabel = UILabel()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        contentView.backgroundColor = .brown
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override func updateConfiguration(using state: UICellConfigurationState) {
        super.updateConfiguration(using: state)
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
    }
}
