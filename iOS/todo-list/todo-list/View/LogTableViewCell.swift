//
//  LogTableViewCell.swift
//  todo-list
//
//  Created by Jason on 2022/04/07.
//

import Foundation
import UIKit

class LogTableViewCell: UITableViewCell {
    static let identifier = "logTableViewCell"
    
    var taskId: Int?
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    enum Font {
        static let title = UIFont(name: "NotoSansKR-Bold", size: 16)
        static let content = UIFont(name: "NotoSansKR-Regular", size: 14)
        static let timeLog = UIFont(name: "NotoSansKR-Regular", size: 12)
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
        
        static let timeLog: [NSMutableAttributedString.Key: Any] = [
            .foregroundColor : UIColor.gray,
            .font : Font.timeLog ?? UIFont()
        ]
    }
    
    func configure(with viewModel: logCellViewModel) {
        self.taskId = viewModel.id
        
        var config = defaultContentConfiguration()
        var backgroundConfig = UIBackgroundConfiguration.listPlainCell()
        
        config.attributedText = self.makePrimaryText(title: viewModel.title)
        config.secondaryAttributedText = self.makeSecondaryText(contents: viewModel.content, timeLog: viewModel.timeLog)
        
        backgroundConfig.cornerRadius = 10
        
        self.contentConfiguration = config
        self.backgroundConfiguration = backgroundConfig
    }
    
    func makePrimaryText(title: String) -> NSAttributedString {
        return NSAttributedString(string: title, attributes: Attributes.title)
    }
    
    func makeSecondaryText(contents: String, timeLog: String) -> NSAttributedString {
        let contentText = NSMutableAttributedString.init(string: "\(contents)", attributes: Attributes.content)
        let newLine = NSAttributedString(string: "\n\n")
        let timeLogText = NSMutableAttributedString.init(string: "\(timeLog)", attributes: Attributes.timeLog)
        
        contentText.append(newLine)
        contentText.append(timeLogText)
        
        return contentText
    }
}


