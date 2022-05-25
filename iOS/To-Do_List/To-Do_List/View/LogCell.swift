//
//  LogCell.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/11.
//

import UIKit

class LogCell : UITableViewCell,CellIdentifiable{
    
    static let identifier = "LogCell"

    private var container: UIView = {
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
    
    private var image: UIImageView = {
        let imageSize = CGSize(width: 40.0, height: 40.0)
        let imageColor = UIColor.white
        let fontSize = 40.0
        
        let imageView = UIImageView()
        let image = "U+1f618".image(size: imageSize, color: imageColor, fontSize: fontSize)
        imageView.image = image
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()
    
     private var authorLabel:UILabel = {
         let label = UILabel()
         label.font = UIFont(name:"Noto Sans Kannada", size: 12.0)
         label.text = "@KaiKim"
        return label
    }()
    
    private var bodyLabel:UILabel = {
        let label = UILabel()
        label.font = UIFont(name:"Noto Sans Kannada", size: 14.0)
        label.text = "(제목) 을/를 (boardType) 에서 (boardType) 으로 이동 하였습니다."
        label.numberOfLines = 3
        label.lineBreakMode = .byWordWrapping
        return label
    }()


     private var dateLabel:UILabel = {
        let label = UILabel()
        label.font = UIFont(name:"Noto Sans Kannada", size: 12.0)
        label.text = "2022-04-08T15:03:37"
        label.textColor = .lightGray
        return label
    }()
    
    
    func loadCardInfo(info : Todo) {
        self.authorLabel.text = info.title
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
        [authorLabel,bodyLabel,dateLabel].forEach {
            stackView.addArrangedSubview($0)
        }
        container.addSubview(stackView)
        container.addSubview(image)
        self.contentView.addSubview(container)
    }
    
    
    private func setup() {
        
        self.backgroundColor = .secondarySystemBackground
        self.contentView.backgroundColor = .secondarySystemBackground
        
        let inset:CGFloat = 16
        stackView.distribution = .fillProportionally
        self.selectionStyle = .none
        
        NSLayoutConstraint.activate([
             
            container.leadingAnchor.constraint(equalTo: self.contentView.leadingAnchor,constant: inset/2),
            container.trailingAnchor.constraint(equalTo: self.contentView.trailingAnchor,constant: -inset/2),
            container.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor,constant: -inset/2),
            container.topAnchor.constraint(equalTo: self.contentView.topAnchor,constant: inset/2),
            
            image.leadingAnchor.constraint(equalTo: container.leadingAnchor, constant: 16),
            image.topAnchor.constraint(equalTo: container.topAnchor, constant: 16),
            image.trailingAnchor.constraint(equalTo: stackView.leadingAnchor),
            image.bottomAnchor.constraint(equalTo: container.bottomAnchor,constant: -100),

            stackView.trailingAnchor.constraint(equalTo: container.trailingAnchor,constant: -inset/2),
            stackView.bottomAnchor.constraint(equalTo: container.bottomAnchor,constant: -inset/2),
            stackView.topAnchor.constraint(equalTo: container.topAnchor,constant: 16)

        ])
    }
}
