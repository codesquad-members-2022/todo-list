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
    }
    
    func updateCount(_ numberOfCards: Int?) {
        guard let count = numberOfCards else {return}
        self.badge.setTitle("\(count)", for: .normal)
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
        addButton.addTarget(self, action: #selector(showAddCardView), for: .touchUpInside)
    }
    
    @objc func showAddCardView() {
        print("Show Button Tapped!")
    }
    
    
    private func addElement() {
        self.addSubview(title)
        self.addSubview(badge)
        self.addSubview(addButton)
    }
    
    private func setConstraints() {
        let inset:CGFloat = 8.0
        let badgeSize:CGFloat = 26.0
        let titleAdjustInset:CGFloat = 4.0
        
        
        NSLayoutConstraint.activate([
            
            title.leadingAnchor.constraint(equalTo: self.leadingAnchor,constant: inset),
            title.centerYAnchor.constraint(equalTo: self.centerYAnchor,constant: titleAdjustInset),
            
            badge.leadingAnchor.constraint(equalTo: title.trailingAnchor, constant: inset),
            badge.centerYAnchor.constraint(equalTo: self.centerYAnchor),
            badge.heightAnchor.constraint(equalToConstant: badgeSize),
            badge.widthAnchor.constraint(equalToConstant: badgeSize),
            
            
            addButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -inset),
            addButton.topAnchor.constraint(equalTo: self.topAnchor, constant: inset),
            addButton.centerYAnchor.constraint(equalTo: self.centerYAnchor)
        ])
    }
}
