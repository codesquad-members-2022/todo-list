//
//  KanbanTableViewCell.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/05.
//

import UIKit

class KanbanTableViewCell: UITableViewCell {
    
    static let indentifier = Constant.TableViewCellIdentifier.kanban
    
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
        label.font = UIFont(name: Constant.Font.gothicNeoBold, size: 16)
        label.textColor = .black
        return label
    }()
    
    private let contentLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont(name: Constant.Font.gothicNeo, size: 14)
        label.textColor = .black
        label.numberOfLines = 3
        return label
    }()
    
    private let authorLabel: UILabel = {
        let label = UILabel()
        label.text = Constant.Text.authorByIOS
        label.font = UIFont(name: Constant.Font.gothicNeo, size: 12)
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

// MARK: - Functions

extension KanbanTableViewCell {
    
    func changeTitleLabel(text: String) {
        titleLabel.text = text
    }
    
    func changeContentLabel(text: String) {
        contentLabel.text = text
    }
}
