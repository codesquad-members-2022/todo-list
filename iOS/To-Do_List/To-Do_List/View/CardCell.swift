//
//  CardCell.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//

import UIKit

class CardCell: UITableViewCell {

    static let identifier = "CardCell"
    
     var stackView:UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        return stackView
    }()
    
     private var headLabel:UILabel = {
        let label = UILabel()
        label.font = UIFont(name:"Noto Sans Kannada", size: 16.0)
        label.text = "오늘 할일"
        return label
    }()
    
     private var bodyLabel:UILabel = {
        let label = UILabel()
        label.font = UIFont(name:"Noto Sans Kannada", size: 14.0)
        label.text = "끝내 주게 쉬기  asdadasdaqwdwdzsdas.m,nz.bvmz lrngvlmr vlkjrb vlkj"
        label.numberOfLines = 3
        return label
    }()


     private var authorLabel:UILabel = {
        let label = UILabel()
        label.font = UIFont(name:"Noto Sans Kannada", size: 12.0)
        label.text = "author by iOS"
        label.textColor = .lightGray
        return label
    }()
    
    
    func loadCardInfo(info : Todo) {
        self.headLabel.text = info.title
        self.bodyLabel.text = info.content
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        addViews()
        setup()
        let inset = UIEdgeInsets(top: 8, left: 0, bottom: 8, right: 0)
        self.contentView.frame = contentView.frame.inset(by: inset)
        self.contentView.layer.cornerRadius = 8
        self.contentView.backgroundColor = .white
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
        stackView.translatesAutoresizingMaskIntoConstraints = false
        [headLabel,bodyLabel,authorLabel].forEach {
            stackView.addArrangedSubview($0)
        }
        self.contentView.addSubview(stackView)
    }
    
    
    private func setup() {
        
        let spacing:CGFloat = 8.0
        let inset:CGFloat = 16
    
        stackView.spacing = spacing
        self.selectionStyle = .none
        self.backgroundColor = .secondarySystemBackground
        
        NSLayoutConstraint.activate([
            stackView.leadingAnchor.constraint(equalTo: self.leadingAnchor,constant: inset),
            stackView.trailingAnchor.constraint(equalTo: self.trailingAnchor,constant: -inset),
            stackView.bottomAnchor.constraint(equalTo: self.bottomAnchor,constant: -inset),
            stackView.topAnchor.constraint(equalTo: self.topAnchor,constant: inset)
        ])
    }
}
