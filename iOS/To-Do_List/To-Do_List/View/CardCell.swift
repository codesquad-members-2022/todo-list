//
//  CardCell.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//

import UIKit

class CardCell: UITableViewCell {

    private let inset:CGFloat = 24.0
    
    lazy var stackView:UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        return stackView
    }()
    
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

    lazy private var writerLabel:UILabel = {
        let label = UILabel()
        label.font = UIFont(name:"Noto Sans Kannada", size: 14.0)
        label.text = "ahthor by iOS"
        label.textColor = .lightGray
        return label
    }()
    
    
    func setCardText(title:String,body:String,writer:String) {
        self.headLabel.text = title
        self.bodyLabel.text = body
        self.writerLabel.text = writer
    }
    
    
    override func awakeFromNib() {
        addViews()
        setup()
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        let inset = UIEdgeInsets(top: 8, left: 16, bottom: 8, right: 16)
        self.contentView.frame = contentView.frame.inset(by: inset)
        self.contentView.backgroundColor = .white
        self.contentView.layer.cornerRadius = 10
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
    
    private func addViews() {
        [headLabel,bodyLabel,writerLabel].forEach {
            $0.translatesAutoresizingMaskIntoConstraints = false
            stackView.addArrangedSubview($0)
        }
        stackView.translatesAutoresizingMaskIntoConstraints = false
        self.addSubview(stackView)
    }
    
    
    private func setup() {
        
        let spacing:CGFloat = 8.0
        
        stackView.spacing = spacing
        
        self.selectionStyle = .none
        self.backgroundColor = .secondarySystemBackground
        
        stackView.leadingAnchor.constraint(equalTo: self.leadingAnchor,constant: inset).isActive = true
        stackView.trailingAnchor.constraint(equalTo: self.trailingAnchor,constant: inset).isActive = true
        stackView.bottomAnchor.constraint(equalTo: self.bottomAnchor,constant: -inset).isActive = true
        stackView.topAnchor.constraint(equalTo: self.topAnchor,constant: inset).isActive = true
    }
    
    
}
