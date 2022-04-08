//
//  InspectorView.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/07.
//

import UIKit

class InspectorViewCell: UITableViewCell {
    
    static let indentifier = "TodoTableViewCell"
    
    private let inspectorStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        stackView.spacing = 8
        stackView.alignment = .leading
        stackView.distribution = .equalSpacing
        return stackView
    }()
    
    private let emojiLabel: UILabel = {
        let label = UILabel()
        label.textAlignment = .center
        label.font = UIFont(name: Constant.Font.gothicNeo, size: 28)
        return label
    }()
    
    private let userLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont(name: Constant.Font.gothicNeo, size: 16)
        label.textColor = .darkGray
        return label
    }()
    
    private let contentsLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont(name: Constant.Font.gothicNeo, size: 16)
        label.textColor = .black
        label.numberOfLines = 2
        return label
    }()
    
    private let timeLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont(name: Constant.Font.gothicNeo, size: 14)
        label.textColor = .gray
        return label
    }()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setUpView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUpView()
    }
    
    private func setUpView() {
        contentView.addSubview(inspectorStackView)
        contentView.addSubview(emojiLabel)
        
        inspectorStackView.addArrangedSubview(userLabel)
        inspectorStackView.addArrangedSubview(contentsLabel)
        inspectorStackView.addArrangedSubview(timeLabel)
        
        layoutInspectorStackView()
        layoutEmojiLabel()
    }

    private func layoutInspectorStackView() {
        inspectorStackView.translatesAutoresizingMaskIntoConstraints = false
        
        inspectorStackView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16).isActive = true
        inspectorStackView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -16).isActive = true
        inspectorStackView.leadingAnchor.constraint(equalTo: emojiLabel.trailingAnchor, constant: 16).isActive = true
        inspectorStackView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16).isActive = true
    }
    
    private func layoutEmojiLabel() {
        emojiLabel.translatesAutoresizingMaskIntoConstraints = false
    
        emojiLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16).isActive = true
        emojiLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16).isActive = true
        emojiLabel.widthAnchor.constraint(equalToConstant: 40).isActive = true
        emojiLabel.heightAnchor.constraint(equalToConstant: 40).isActive = true
    }
}
extension InspectorViewCell {
    func changeEmojiLabel(text: String) {
        emojiLabel.text = text
    }
    
    func changeUserLabel(text: String) {
        userLabel.text = text
    }
    
    func changeContentsLabel(text: String) {
        contentsLabel.text = text
    }
    
    func changeTimeLabel(text: String) {
        timeLabel.text = text
    }
}
