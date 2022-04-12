//
//  MenuView.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/11.
//

import UIKit

final class MenuActivityCell: UITableViewCell{
    
    private var emoji: UILabel!
    private var authorLabel: UILabel!
    private var contentLabel: UILabel!
    private var timeLabel: UILabel!
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?){
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        self.backgroundColor = .white
        setAttributes()
    }
    
    required init?(coder: NSCoder){
        super.init(coder: coder)
        self.backgroundColor = .white
        setAttributes()
    }
    
    func setLabelText(author: String, content: String, time: String){
        self.authorLabel.text = author
        self.contentLabel.text = content
        self.timeLabel.text = time
    }
}

private extension MenuActivityCell{
    func setAttributes(){
        configureEmoji()
        configureAuthorLabel()
        configureContentLabel()
        configureTimeLabel()
    }
    
    
    func configureEmoji(){
        
        emoji = UILabel()
        emoji.text = "😎"
        self.contentView.addSubview(emoji)
        
        emoji.font = UIFont.systemFont(ofSize: 40)
        emoji.translatesAutoresizingMaskIntoConstraints = false
        emoji.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor, constant: 16).isActive = true
        emoji.topAnchor.constraint(equalTo: self.contentView.topAnchor, constant: 16).isActive = true
        emoji.widthAnchor.constraint(equalToConstant: 40).isActive = true
        emoji.heightAnchor.constraint(equalToConstant: 40).isActive = true
    }
    
    func configureAuthorLabel(){
        authorLabel = UILabel()
        authorLabel.textColor = .black
        authorLabel.font = UIFont(name: "NotoSansKR-Regular", size: 16)
        self.contentView.addSubview(authorLabel)
        
        authorLabel.translatesAutoresizingMaskIntoConstraints = false
        authorLabel.topAnchor.constraint(equalTo: emoji.topAnchor).isActive = true
        authorLabel.leadingAnchor.constraint(equalTo: emoji.trailingAnchor, constant: 16).isActive = true
        authorLabel.heightAnchor.constraint(equalToConstant: 23).isActive = true
    }
    
    func configureContentLabel(){
        contentLabel = UILabel()
        contentLabel.textColor = .black
        contentLabel.numberOfLines = 0
        contentLabel.lineBreakMode = .byCharWrapping
        contentLabel.font = UIFont(name: "NotoSansKR-Regular", size: 16)
        self.contentView.addSubview(contentLabel)
        
        contentLabel.translatesAutoresizingMaskIntoConstraints = false
        contentLabel.topAnchor.constraint(equalTo: authorLabel.bottomAnchor, constant: 8).isActive = true
        contentLabel.leadingAnchor.constraint(equalTo: authorLabel.leadingAnchor).isActive = true
        contentLabel.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor, constant: -16).isActive = true
        
    }
    
    func configureTimeLabel(){
        timeLabel = UILabel()
        timeLabel.textColor = UIColor(red: 0.51, green: 0.51, blue: 0.51, alpha: 1)
        timeLabel.font = UIFont(name: "NotoSansKR-Regular", size: 14)
        self.contentView.addSubview(timeLabel)
        
        timeLabel.translatesAutoresizingMaskIntoConstraints = false
        timeLabel.topAnchor.constraint(equalTo: contentLabel.bottomAnchor, constant: 8).isActive = true
        timeLabel.leadingAnchor.constraint(equalTo: authorLabel.leadingAnchor).isActive = true
        timeLabel.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor, constant: -16).isActive = true
        timeLabel.heightAnchor.constraint(equalToConstant: 20).isActive = true
    }
}
