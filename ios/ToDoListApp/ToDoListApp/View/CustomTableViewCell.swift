//
//  ToDoTableViewCell.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/05.
//

import UIKit

class CustomTableViewCell: UITableViewCell {
    
    static let indentifier = "TodoTableViewCell"
    
    private let stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        stackView.spacing = 8
        stackView.alignment = .leading
        stackView.distribution = .equalSpacing
        return stackView
    }()
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont(name: "Apple SD Gothic Neo Bold", size: 16)
        label.textColor = .black
        return label
    }()
    
    private let contentLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont(name: "Apple SD Gothic Neo", size: 14)
        label.textColor = .black
        return label
    }()
    
    private let authorLabel: UILabel = {
        let label = UILabel()
        label.text = "author by iOS"
        label.font = UIFont(name: "Apple SD Gothic Neo", size: 12)
        label.textColor = .lightGray
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
        configureView()
        contentView.addSubview(stackView)
        
        stackView.addArrangedSubview(titleLabel)
        stackView.addArrangedSubview(contentLabel)
        stackView.addArrangedSubview(authorLabel)
        
        layoutStackView()
    }
    
    private func configureView() {
        self.layer.cornerRadius = 7
        self.clipsToBounds = true
    }
    
    private func layoutStackView() {
        stackView.translatesAutoresizingMaskIntoConstraints = false
        
        stackView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16).isActive = true
        stackView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -16).isActive = true
        stackView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16).isActive = true
        stackView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16).isActive = true
    }
}

// MARK: - functions

extension CustomTableViewCell {
    func changCustomTableViewCell() {
        self.layer.cornerRadius = 7
        self.clipsToBounds = true
    }
    
    func changeTitleLabel(text: String) {
        titleLabel.text = text
    }
    
    func changeContentLabel(text: String) {
        contentLabel.text = text
    }
}
