//
//  CardCell.swift
//  To-Do_List
//
//  Created by 박진섭 on 2022/04/05.
//



protocol CellIdentifiable {
    static var identifier : String {get}
}

import UIKit

class CardCell: UITableViewCell, CellIdentifiable {

    static let identifier = "CardCell"
    
    private var container : UIView = {
        let label = UIView()
        label.backgroundColor = .white
        label.layer.cornerRadius = 8
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
     private var stackView:UIStackView = {
        let stackView = UIStackView()
         stackView.axis = .vertical
         stackView.backgroundColor = .white
        
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
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        addViews()
        setup()
    }
    
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        if selected {
            container.layer.borderWidth = 2.0
            container.layer.borderColor = UIColor.blue.cgColor
        } else {
            container.layer.borderWidth = 0.0
        }
    }
    
    private func addViews() {
        [headLabel,bodyLabel,authorLabel].forEach {
            $0.translatesAutoresizingMaskIntoConstraints = false
            stackView.addArrangedSubview($0)
        }
        container.addSubview(stackView)
        self.contentView.addSubview(container)
    }
    
    
    private func setup() {
        
        self.backgroundColor = .secondarySystemBackground
        self.contentView.backgroundColor = .secondarySystemBackground
        
        let spacing:CGFloat = 8.0
        let inset:CGFloat = 16
        stackView.spacing = spacing
        self.selectionStyle = .none
        

        NSLayoutConstraint.activate([
            
            container.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor,constant: inset/2),
            container.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor,constant: -inset/2),
            container.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor,constant: -inset/2),
            container.topAnchor.constraint(equalTo: self.contentView.topAnchor,constant: inset/2),
            
            stackView.leadingAnchor.constraint(equalTo: container.leadingAnchor,constant: inset/2),
            stackView.trailingAnchor.constraint(equalTo: container.trailingAnchor,constant: -inset/2),
            stackView.bottomAnchor.constraint(equalTo: container.bottomAnchor,constant: -inset/2),
            stackView.topAnchor.constraint(equalTo: container.topAnchor,constant: inset/2)
        ])
    }
}
