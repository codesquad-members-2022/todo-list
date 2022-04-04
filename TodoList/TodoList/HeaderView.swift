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
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setHeaderLabel()
    }
    
    func setHeaderLabel(){
        headerLabel = UILabel()
        headerLabel.text = "To-Do List"
        headerLabel.font = UIFont.systemFont(ofSize: 45)
        headerLabel.textColor = .black
        
        self.addSubview(headerLabel)
        
        headerLabel.translatesAutoresizingMaskIntoConstraints = false
        headerLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 50).isActive = true
        headerLabel.centerYAnchor.constraint(equalTo:self.centerYAnchor).isActive = true
        headerLabel.heightAnchor.constraint(equalToConstant: 300).isActive = true
    }
}
