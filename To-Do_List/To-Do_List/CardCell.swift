//
//  CardCell.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//

import UIKit

class CardCell: UITableViewCell {

    lazy private var headLabel:UILabel = {
        let label = UILabel()
        label.font = UIFont(name:"Noto Sans Kannada", size: 16.0)
        label.text = "오늘 할일"
        return label
    }()
    
    lazy private var bodyLabel:UILabel = {
        let label = UILabel()
        label.font = UIFont(name:"Noto Sans Kannada", size: 14.0)
        label.text = "끝내 주게 쉬기."
        return label
    }()

    override func awakeFromNib() {
        setup()
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        if selected {
            contentView.layer.borderWidth = 2.0
            contentView.layer.borderColor = UIColor.blue.cgColor
        } else {
            contentView.layer.borderWidth = 0.0
        }
    }
    
    private func setup() {
        let inset:CGFloat = 16.0
        
        
        [headLabel,bodyLabel].forEach {
            $0.translatesAutoresizingMaskIntoConstraints = false
            self.addSubview($0)
        }
        
        headLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: inset).isActive = true
        headLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: inset).isActive = true
        
        bodyLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: inset).isActive = true
        bodyLabel.topAnchor.constraint(equalTo: self.headLabel.bottomAnchor, constant: 8).isActive = true
        bodyLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -inset).isActive = true
        
    }
    
    
}
