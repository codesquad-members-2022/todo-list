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
         stackView.backgroundColor = .white
         stackView.layer.cornerRadius = 8
         stackView.translatesAutoresizingMaskIntoConstraints = false
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
        label.text = "끝내 주게 쉬기dasdasdasdasdasdasddasdasdasdasdasdasddasdasdasdasdasdasddasdasdasdasdasdasd  "
        label.numberOfLines = 3
        label.lineBreakMode = .byWordWrapping
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
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        addViews()
        setup()
    }
    
//    override func layoutSubviews() {
//        super.layoutSubviews()
//        stackView.spacing = 8
//        let inset:CGFloat = 8
//        self.contentView.frame = contentView.frame.inset(by: UIEdgeInsets(top: 8, left: 0, bottom: 8, right: 0))
//        NSLayoutConstraint.activate([
////            contentView.topAnchor.constraint(equalTo: stackView.topAnchor),
////            contentView.bottomAnchor.constraint(equalTo: stackView.bottomAnchor)
//            stackView.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor,constant: inset),
//            stackView.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor,constant: -inset),
//            stackView.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor,constant: -inset),
//            stackView.topAnchor.constraint(equalTo: self.contentView.topAnchor,constant: inset)
//        ])
//    }
//
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        addViews()
        setup()
    }
    
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        if selected {
            stackView.layer.borderWidth = 2.0
            stackView.layer.borderColor = UIColor.blue.cgColor
        } else {
            stackView.layer.borderWidth = 0.0
        }
    }
    
    private func addViews() {
        [headLabel,bodyLabel,authorLabel].forEach {
            $0.translatesAutoresizingMaskIntoConstraints = false
            stackView.addArrangedSubview($0)
        }
        self.contentView.addSubview(stackView)
    }
    
    
    private func setup() {
        
        self.backgroundColor = .secondarySystemBackground
        self.contentView.backgroundColor = .secondarySystemBackground
        
        let spacing:CGFloat = 8.0
        let inset:CGFloat = 16
        stackView.spacing = spacing
        self.selectionStyle = .none
        

        NSLayoutConstraint.activate([
            stackView.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor,constant: inset/2),
            stackView.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor,constant: -inset/2),
            stackView.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor,constant: -inset/2),
            stackView.topAnchor.constraint(equalTo: self.contentView.topAnchor,constant: inset/2),
            
            headLabel.topAnchor.constraint(equalTo: stackView.topAnchor, constant: inset),
            headLabel.leadingAnchor.constraint(equalTo: stackView.leadingAnchor, constant: inset),
            headLabel.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor, constant: -inset),
            authorLabel.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor, constant: -inset - 8)
        ])
    }
}
