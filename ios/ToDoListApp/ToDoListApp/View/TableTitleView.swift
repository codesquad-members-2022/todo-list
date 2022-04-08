//
//  TableTitleView.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/05.
//

import UIKit

class TableTitleView: UIView {
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont(name: Constant.Font.gothicNeo, size: 18)
        label.textColor = .black
        return label
    }()
    
    private let badgeLabel: UILabel = {
        let label = UILabel()
        label.backgroundColor = .lightGray
        label.textColor = .black
        label.font = UIFont(name: Constant.Font.gothicNeo, size: 14)
        label.layer.cornerRadius = 13
        label.layer.masksToBounds = true
        label.textAlignment = .center
        return label
    }()
    
    private let addButton: UIButton = {
        let button = UIButton(type: .system)
        button.setImage(UIImage(systemName: "plus"), for: .normal)
        button.tintColor = .gray
        return button
    }()
    
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUpView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUpView()
    }
    
    private func setUpView() {
        addSubview(titleLabel)
        addSubview(badgeLabel)
        addSubview(addButton)
        
        layoutTitleLabel()
        layoutBadgeLabel()
        layoutAddButton()
    }
    
    private func layoutTitleLabel() {
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        
        titleLabel.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        titleLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 16).isActive = true
    }
    
    private func layoutBadgeLabel() {
        badgeLabel.translatesAutoresizingMaskIntoConstraints = false
        
        badgeLabel.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        badgeLabel.leadingAnchor.constraint(equalTo: titleLabel.trailingAnchor, constant: 8).isActive = true
        badgeLabel.widthAnchor.constraint(equalToConstant: 26).isActive = true
        badgeLabel.heightAnchor.constraint(equalToConstant: 26).isActive = true
    }
    
    private func layoutAddButton() {
        addButton.translatesAutoresizingMaskIntoConstraints = false
        
        addButton.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -16).isActive = true
        addButton.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
    }
}

// MARK: - functions

extension TableTitleView {
    func changeBadgeLabel(text count: Int){
        badgeLabel.text = "\(count)"
    }
    
    func changeTitleLabel(text: String) {
        titleLabel.text = text
    }
}
