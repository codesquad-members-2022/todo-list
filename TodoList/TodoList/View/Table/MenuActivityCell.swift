//
//  MenuView.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/11.
//

import UIKit

final class MenuActivityCell: UITableViewCell {
    
    private var emoji: UILabel!
    private var authorLabel: UILabel!
    private var contentLabel: UILabel!
    private var timeLabel: UILabel!
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?){
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        self.backgroundColor = UIColor(red: 1.0, green: 0, blue: 0, alpha: 1)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.backgroundColor = UIColor(red: 1.0, green: 0, blue: 0, alpha: 1)
    }
    
    func setLabelText(author: String, content: String, time: String){
        self.authorLabel.text = author
        self.contentLabel.text = content
        self.timeLabel.text = time
    }
    

}
