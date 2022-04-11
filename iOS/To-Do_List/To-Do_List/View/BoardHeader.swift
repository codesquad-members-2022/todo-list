//
//  BoardHeader.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/05.
//

import UIKit

protocol headerDisplayable {
    func setUp(title : String)
}

class BoardHeader: UIView {

    private (set) var title = UILabel()
    private (set) var badge = UIButton()
    private (set) var addButton = UIButton()
    private (set) var closeButton = UIButton()

    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    
    convenience init(titleText: BoardType) {
        self.init(frame: .zero)
        self.title.text = "\(titleText)"
        setupView()
        self.translatesAutoresizingMaskIntoConstraints = false
    }
    
    func updateCount(_ numberOfCards: Int) {
        self.badge.setTitle("\(numberOfCards)", for: .normal)
    }

    private func setupView() {
        setTitle()
        setBadge()
        setAddButton()
        addElement()
        setConstraints()
    }
 
    private func setTitle() {
        title.font = UIFont(name:"Noto Sans Kannada Bold", size: 18)
        title.textAlignment = .center
        title.sizeToFit()
        title.translatesAutoresizingMaskIntoConstraints = false
    }
    
    private func setBadge() {
        badge.layer.cornerRadius = 26 / 2
        badge.setTitleColor(.black, for: .normal)
        badge.backgroundColor = .systemGray
        badge.setTitle("0", for: .normal)
        badge.translatesAutoresizingMaskIntoConstraints = false
    }
    
    private func setAddButton() {
        addButton.translatesAutoresizingMaskIntoConstraints = false
        addButton.setImage(UIImage(systemName: "plus"), for: .normal)
        
    }
    
    
    
    private func addElement() {
        self.addSubview(title)
        self.addSubview(badge)
        self.addSubview(addButton)
    }
    
    private func setConstraints() {
        
        
        NSLayoutConstraint.activate([
            

            title.leadingAnchor.constraint(equalTo: self.leadingAnchor,constant: 8),
            
            badge.centerYAnchor.constraint(equalTo: title.centerYAnchor, constant: -3),
            badge.leadingAnchor.constraint(equalTo: title.trailingAnchor, constant: 8),
            badge.topAnchor.constraint(equalTo: self.topAnchor),
            badge.heightAnchor.constraint(equalToConstant: 26),
            badge.widthAnchor.constraint(equalToConstant: 26),
            
            addButton.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 220),
            addButton.centerYAnchor.constraint(equalTo: title.centerYAnchor, constant: -3),

        ])
    }
}
