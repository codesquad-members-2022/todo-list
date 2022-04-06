//
//  HeaderView.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/04.
//

import Foundation
import UIKit

class HeaderView: UIView{
    private var headerLabel: UILabel!
    private var menuButton: UIButton!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setHeaderLabel()
        setMenuButton()
        self.backgroundColor = .systemGray5
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setHeaderLabel()
        setMenuButton()
        self.backgroundColor = .systemGray
    }
    
    func setHeaderLabel(){
        headerLabel = UILabel()
        headerLabel.text = "To-Do List"
        headerLabel.font = UIFont.systemFont(ofSize: 45)
        headerLabel.textColor = .black
        
        self.addSubview(headerLabel)
        
        headerLabel.translatesAutoresizingMaskIntoConstraints = false
        headerLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 20).isActive = true
        headerLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -10).isActive = true
    }
    
    func setMenuButton(){
        menuButton = UIButton()
        menuButton.setImage(UIImage(systemName: "line.3.horizontal"), for: .normal)
        menuButton.tintColor = .black
        
        let largeConfigure = UIImage.SymbolConfiguration(pointSize: 20, weight: .regular, scale: .large)
        let menuButtonImage = UIImage(systemName: "line.3.horizontal", withConfiguration: largeConfigure)
        menuButton.setImage(menuButtonImage, for: .normal)
        
        self.addSubview(menuButton)
        
        menuButton.translatesAutoresizingMaskIntoConstraints = false
        menuButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -20).isActive = true
        menuButton.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -20).isActive = true
    }
}
