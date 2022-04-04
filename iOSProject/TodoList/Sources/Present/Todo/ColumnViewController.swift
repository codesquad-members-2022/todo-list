//
//  TodoViewController.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Foundation
import UIKit

protocol ColumnView {
    var view: UIView! { get }
}

class ColumnViewController: UIViewController, ColumnView {
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "해야할 일"
        label.font = .systemFont(ofSize: 18, weight: .bold)
        return label
    }()
    
    private let badge: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .gray4
        view.layer.cornerRadius = 13
        return view
    }()
    
    private let count: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "0"
        label.font = .systemFont(ofSize: 14, weight: .bold)
        label.textColor = .black
        return label
    }()
    
    private let add: UIButton = {
        var configuration = UIButton.Configuration.plain()
        configuration.contentInsets = .init(top: 16, leading: 16, bottom: 16, trailing: 16)

        let button = UIButton(configuration: configuration, primaryAction: nil)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setImage(UIImage(named: "ic_add"), for: .normal)
        button.tintColor = .gray4
        return button
    }()
    
    init() {
        super.init(nibName: nil, bundle: nil)
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
        self.view.backgroundColor = .gray
    }
    
    private func layout() {
        self.view.addSubview(titleLabel)
        titleLabel.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        titleLabel.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 8).isActive = true
        
        self.view.addSubview(badge)
        badge.leftAnchor.constraint(equalTo: titleLabel.rightAnchor, constant: 8).isActive = true
        badge.centerYAnchor.constraint(equalTo: titleLabel.centerYAnchor).isActive = true
        badge.widthAnchor.constraint(equalToConstant: 26).isActive = true
        badge.heightAnchor.constraint(equalToConstant: 26).isActive = true
        
        badge.addSubview(count)
        count.centerXAnchor.constraint(equalTo: badge.centerXAnchor).isActive = true
        count.centerYAnchor.constraint(equalTo: badge.centerYAnchor).isActive = true
        
        self.view.addSubview(add)
        add.centerYAnchor.constraint(equalTo: titleLabel.centerYAnchor).isActive = true
        add.rightAnchor.constraint(equalTo: self.view.rightAnchor, constant: -8).isActive = true
        add.widthAnchor.constraint(equalToConstant: 24).isActive = true
        add.heightAnchor.constraint(equalToConstant: 24).isActive = true
    }
}
