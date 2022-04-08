//
//  LogViewCell.swift
//  TodoList
//
//  Created by Joobang Lee on 2022/04/07.
//

import Foundation
import UIKit

class LogViewCell: UITableViewCell{

    private let labelName: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.textColor = .blue
        label.text = "@sam"
        return label
    }()
    
    private let labelContent: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakStrategy = .hangulWordPriority
        label.translatesAutoresizingMaskIntoConstraints = false
        label.textColor = .black
        label.text = "해야할 일에 대한 정보 입력 및 데이터 테스트를 진행해봅니다."
        return label
    }()
    
    private let labelTime: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.textColor = .gray
        label.text = "1분전"
        return label
    }()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?){
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        attribute()
        layout()
    }
    
    required init?(coder: NSCoder){
        super.init(coder: coder)
        attribute()
        layout()
    }
    
    private func attribute(){
        self.contentView.backgroundColor = UIColor(red: (240/255), green: (255/255), blue: (240/255), alpha: 1)
    }
    
    private func layout(){
        contentView.addSubview(labelName)
        contentView.addSubview(labelContent)
        contentView.addSubview(labelTime)
        
        NSLayoutConstraint.activate([
            labelName.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16),
            labelName.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 72),
            labelName.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),
            
            labelContent.topAnchor.constraint(equalTo: labelName.bottomAnchor, constant: 8),
            labelContent.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 72),
            labelContent.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),

            labelTime.topAnchor.constraint(equalTo: labelContent.bottomAnchor, constant: 8),
            labelTime.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 72),
            labelTime.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),

            self.contentView.bottomAnchor.constraint(equalTo: labelTime.bottomAnchor, constant: 16)
        ])
    }
}
