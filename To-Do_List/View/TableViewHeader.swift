//
//  TabelViewHeader.swift
//  To-Do_List
//
//  Created by Kai Kim on 2022/04/05.
//

import UIKit

protocol headerDisplayable {
    func setUp(title : String)
}

class TabelViewHeader: UIView {

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
    
    
    convenience init(titleText: String) {
        self.init(frame: CGRect(x: 0, y: 0, width: 100, height: 30))
        self.title.text = titleText
        title.frame = self.bounds
        setupView()
    }

    private func setupView() {
        setTitle()
        setBadge()
        setaddButton()
        addElement()
        setConstraints()
    }
 
    private func setTitle() {
        title.frame = self.bounds
    }
    
    private func setBadge() {
        badge.translatesAutoresizingMaskIntoConstraints = false
        badge.layer.cornerRadius = 30 / 2
        badge.setTitleColor(.white, for: .normal)
        badge.backgroundColor = .systemBlue
        badge.setTitle("0", for: .normal)
    }
    
    private func setaddButton() {
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
            badge.leadingAnchor.constraint(equalTo: title.trailingAnchor, constant: 8),
            badge.centerYAnchor.constraint(equalTo: title.centerYAnchor),
            
            addButton.leadingAnchor.constraint(equalTo: title.trailingAnchor, constant: 73.11),
            addButton.centerYAnchor.constraint(equalTo: title.centerYAnchor),

            
        ])
    }
}
