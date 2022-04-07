//
//  HeaderView.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/04.
//

import UIKit

class HeaderView: UIView{
    private var headerLabel: UILabel!
    private var menuButton: UIButton!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setHeaderLabel()
        setMenuButton()
        self.layer.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1).cgColor
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setHeaderLabel()
        setMenuButton()
        self.layer.backgroundColor = UIColor(red: 0.961, green: 0.961, blue: 0.969, alpha: 1).cgColor
    }
}

private extension HeaderView{
    func setHeaderLabel(){
        headerLabel = UILabel()
        headerLabel.text = "To-Do List"
        headerLabel.font = UIFont(name: "NotoSansKR-Regular", size: 32)
        headerLabel.textColor = UIColor(red: 0.004, green: 0.004, blue: 0.004, alpha: 1)
        
        self.addSubview(headerLabel)
        
        headerLabel.translatesAutoresizingMaskIntoConstraints = false
        headerLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 48).isActive = true
        headerLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: 13).isActive = true
        headerLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -13).isActive = true
    }
    
    func setMenuButton(){
        menuButton = UIButton()
        menuButton.setImage(UIImage(systemName: "line.3.horizontal"), for: .normal)
        menuButton.tintColor = .black
        
        let largeConfigure = UIImage.SymbolConfiguration(pointSize: 17, weight: .regular, scale: .large)
        let menuButtonImage = UIImage(systemName: "line.3.horizontal", withConfiguration: largeConfigure)
        menuButton.setImage(menuButtonImage, for: .normal)
        
        self.addSubview(menuButton)
        
        menuButton.translatesAutoresizingMaskIntoConstraints = false
        menuButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -52).isActive = true
        menuButton.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
    }
}
