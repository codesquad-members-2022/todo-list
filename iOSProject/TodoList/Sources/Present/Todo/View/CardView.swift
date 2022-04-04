//
//  CardView.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Foundation
import UIKit

class CardView: UIView {
    let title: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "GitHub 공부하기"
        label.font = .systemFont(ofSize: 16, weight: .bold)
        label.textColor = .black
        return label
    }()
    
    let body: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "message"
        label.font = .systemFont(ofSize: 14)
        label.textColor = .black
        return label
    }()
    
    let caption: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "author by iOS"
        label.font = .systemFont(ofSize: 12)
        label.textColor = .gray4
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        bind()
        attribute()
        layout()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        bind()
        attribute()
        layout()
    }
    
    private func bind() {
        
    }
    
    private func attribute() {
        
    }
    
    private func layout() {
        self.addSubview(title)
        title.topAnchor.constraint(equalTo: self.topAnchor, constant: 16).isActive = true
        title.leftAnchor.constraint(equalTo: self.leftAnchor, constant: 16).isActive = true
        title.rightAnchor.constraint(equalTo: self.rightAnchor, constant: -16).isActive = true
        
        self.addSubview(body)
        body.topAnchor.constraint(equalTo: title.bottomAnchor, constant: 8).isActive = true
        body.leftAnchor.constraint(equalTo: self.leftAnchor, constant: 16).isActive = true
        body.rightAnchor.constraint(equalTo: self.rightAnchor, constant: -16).isActive = true
        
        self.addSubview(caption)
        caption.topAnchor.constraint(equalTo: body.bottomAnchor, constant: 8).isActive = true
        caption.leftAnchor.constraint(equalTo: self.leftAnchor, constant: 16).isActive = true
        caption.rightAnchor.constraint(equalTo: self.rightAnchor, constant: -16).isActive = true
        
        self.bottomAnchor.constraint(equalTo: caption.bottomAnchor, constant: 16).isActive = true
    }
}
