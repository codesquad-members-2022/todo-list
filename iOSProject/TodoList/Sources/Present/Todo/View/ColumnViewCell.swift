//
//  CardView.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Foundation
import UIKit

class ColumnViewCell: UITableViewCell {
    let cellBackgroundView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .white
        view.layer.cornerRadius = 6
        return view
    }()
    
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
        let random = Int.random(in: 0..<3)
        label.text = (0..<random).compactMap { _ in "messagemessagemessagemessagemessagemessagemessagemessagemessage"}.joined()
        label.font = .systemFont(ofSize: 14)
        label.textColor = .black
        label.lineBreakMode = .byTruncatingTail
        label.numberOfLines = 3
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
    
//    let spacing: UIView = {
//        let view = UIView()
//        view.backgroundColor = .clear
//        return view
//    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
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
        self.backgroundColor = .clear
    }
    
    private func layout() {
        self.addSubview(cellBackgroundView)
        cellBackgroundView.topAnchor.constraint(equalTo: self.topAnchor).isActive = true
        cellBackgroundView.leftAnchor.constraint(equalTo: self.leftAnchor).isActive = true
        cellBackgroundView.rightAnchor.constraint(equalTo: self.rightAnchor).isActive = true
        
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
        
        cellBackgroundView.bottomAnchor.constraint(equalTo: caption.bottomAnchor, constant: 16).isActive = true
        self.bottomAnchor.constraint(equalTo: cellBackgroundView.bottomAnchor, constant: 16).isActive = true
    }
}
