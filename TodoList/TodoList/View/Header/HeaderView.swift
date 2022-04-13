//
//  HeaderView.swift
//  TodoList
//
//  Created by juntaek.oh on 2022/04/04.
//

import UIKit

final class HeaderView: UIView{
    private var headerLabel: UILabel!
    private var menuButton: UIButton!
    var delegate: HeaderViewDelegate?
    
    override init(frame: CGRect){
        super.init(frame: frame)
        setHeaderLabel()
        setMenuButton()
        self.layer.backgroundColor = ColorMaker.lightGray0.getRawValue().cgColor
    }
    
    required init?(coder: NSCoder){
        super.init(coder: coder)
        setHeaderLabel()
        setMenuButton()
        self.layer.backgroundColor = ColorMaker.lightGray0.getRawValue().cgColor
    }
}

private extension HeaderView{
    func setHeaderLabel(){
        headerLabel = UILabel()
        headerLabel.text = "To-Do List"
        headerLabel.font = UIFont(name: "NotoSansKR-Regular", size: 32)
        headerLabel.textColor = ColorMaker.black.getRawValue()
        
        self.addSubview(headerLabel)
        
        headerLabel.translatesAutoresizingMaskIntoConstraints = false
        headerLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 48).isActive = true
        headerLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: 13).isActive = true
        headerLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: -13).isActive = true
    }
    
    func setMenuButton(){
        menuButton = UIButton()
        menuButton.tintColor = ColorMaker.black.getRawValue()
        menuButton.setImage(UIImage(named: "menu"), for: .normal)
        
        self.addSubview(menuButton)
        
        menuButton.translatesAutoresizingMaskIntoConstraints = false
        menuButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -52).isActive = true
        menuButton.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
        menuButton.addTarget(self, action: #selector(touchedMenuButton(_:)), for: .touchUpInside)
    }
    
    @objc
    func touchedMenuButton(_ button: UIButton){
        self.delegate?.headerMenuButtonDidTouched()
    }
}
