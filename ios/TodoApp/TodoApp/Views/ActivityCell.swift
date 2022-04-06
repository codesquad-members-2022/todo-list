//
//  ActivityCell.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/06.
//

import UIKit

class ActivityCell: UITableViewCell {
    static let identifier = "ActivityCell"
    
    private let horizontalStackView: UIStackView = {
        let stack = UIStackView()
        stack.axis = .horizontal
        stack.alignment = .top
        stack.spacing = 20
        stack.translatesAutoresizingMaskIntoConstraints = false
        return stack
    }()
    
    private let verticalStackView: UIStackView = {
        let stack = UIStackView()
        stack.axis = .vertical
        stack.spacing = 10
        return stack
    }()
    
    private let thumnail: UILabel = {
        let label = UILabel(frame: .zero)
        label.text = "🥳"
        label.textAlignment = .center
        label.font = .systemFont(ofSize: 36)
        
        return label
    }()
    
    private let nameLabel: UILabel = {
        let label = UILabel(frame: .zero)
        return label
    }()
    
    private let messageLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.numberOfLines = 0
        return label
    }()
    
    private let dateLabel: UILabel = {
        let label = UILabel()
        return label
    }()

    override func awakeFromNib() {
        super.awakeFromNib()
        self.configureCell()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.configureCell()
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        self.configureCell()
    }

    private func configureCell() {
        self.contentView.backgroundColor = UIColor(red: 109/255, green: 208/255, blue: 40/255, alpha: 0.1)
        
        self.nameLabel.text = "@Alex"
        self.messageLabel.text = "HTML/CSS 공부하기를 해야할 일에서 하고 있는 일로 이동하였습니다."
        self.dateLabel.text = "1분 전"

        self.verticalStackView.addArrangedSubview(self.nameLabel)
        self.verticalStackView.addArrangedSubview(self.messageLabel)
        self.verticalStackView.addArrangedSubview(self.dateLabel)
        
        self.horizontalStackView.addArrangedSubview(self.thumnail)
        self.horizontalStackView.addArrangedSubview(self.verticalStackView)
        self.contentView.addSubview(self.horizontalStackView)
        
        self.horizontalStackView.topAnchor.constraint(equalTo: self.contentView.topAnchor,constant:  20).isActive = true
        self.horizontalStackView.bottomAnchor.constraint(equalTo: self.contentView.bottomAnchor, constant: -20).isActive = true
        self.horizontalStackView.rightAnchor.constraint(equalTo: self.contentView.rightAnchor, constant: -20).isActive = true
        self.horizontalStackView.leftAnchor.constraint(equalTo: self.contentView.leftAnchor, constant:  20).isActive = true
    }
    
    private func setHeaderText(_ text: String) {
        self.nameLabel = text
    }
    
    private func setFooterText(_ text: String) {
        self.nameLabel = text
    }
    
    private func setBodyText(_ text: String) {
        self.nameLabel = text
    }
    
}
