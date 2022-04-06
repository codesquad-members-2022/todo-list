//
//  TitleView.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/05.
//

import UIKit

class TitleView: UIView {
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.text = "ToDo-List"
        label.font = UIFont(name: "Apple SD Gothic Neo Bold", size: 32)
        return label
    }()
    
    private let inspectorButton: UIButton = {
        let button = UIButton(type: .system)
        button.setImage(UIImage(named: "InspectorButton"), for: .normal)
        button.tintColor = .black
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
        addSubview(inspectorButton)
        
        layoutTitleLabel()
        layoutInspectorButton()
    }
    
    private func layoutTitleLabel() {
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        
        titleLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 48).isActive = true
        titleLabel.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
    }
    
    private func layoutInspectorButton() {
        inspectorButton.translatesAutoresizingMaskIntoConstraints = false
        
        inspectorButton.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -52).isActive = true
        inspectorButton.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        inspectorButton.heightAnchor.constraint(equalToConstant: 11).isActive = true
    }
}
