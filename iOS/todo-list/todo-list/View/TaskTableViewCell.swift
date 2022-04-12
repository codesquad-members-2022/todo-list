//
//  TaskTableViewCell.swift
//  todo-list
//
//  Created by Bumgeun Song on 2022/04/05.
//

import UIKit

class TaskTableViewCell: UITableViewCell {
    static let identifier = "taskTableViewCell"
    
    var id: Int?
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    enum Font {
        static let title = UIFont(name: "NotoSansKR-Bold", size: 16)
        static let content = UIFont(name: "NotoSansKR-Regular", size: 14)
        static let authored = UIFont(name: "NotoSansKR-Regular", size: 12)
    }
    
    enum Attributes {
        static let title: [NSMutableAttributedString.Key: Any] = [
            .foregroundColor : UIColor.black,
            .font : Font.title ?? UIFont()
        ]
        
        static let content: [NSMutableAttributedString.Key: Any] = [
            .foregroundColor : UIColor.black,
            .font : Font.content ?? UIFont()
        ]
        
        static let authored: [NSMutableAttributedString.Key: Any] = [
            .foregroundColor : UIColor.gray,
            .font : Font.authored ?? UIFont()
        ]
    }
    
    func configure(with viewModel: TaskCellViewModel) {
        
        self.id = viewModel.id
        
        var config = defaultContentConfiguration()
        
        config.attributedText = self.makePrimaryText(title: viewModel.title)
        
        config.secondaryAttributedText = self.makeSecondaryText(contents: viewModel.content, authored: "Authored by \(viewModel.device)")
        
        self.contentConfiguration = config
        
        var backgroundConfig = UIBackgroundConfiguration.listPlainCell()
        backgroundConfig.cornerRadius = 10
        
        self.backgroundConfiguration = backgroundConfig
    }
    
    func makePrimaryText(title: String) -> NSAttributedString {
        return NSAttributedString(string: title, attributes: Attributes.title)
    }
    
    func makeSecondaryText(contents: String, authored: String) -> NSAttributedString {
        
        let contentText = NSMutableAttributedString.init(string: "\(contents)", attributes: Attributes.content)
        let newLine = NSAttributedString(string: "\n\n")
        let authoredText = NSMutableAttributedString.init(string: "\(authored)", attributes: Attributes.authored)
        
        contentText.append(newLine)
        contentText.append(authoredText)
        
        return contentText
    }
}
