//
//  MainTitleBarView.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Foundation
import UIKit
import Combine

protocol MainTitleBar {
    var view: UIView { get }
    var menuPublisher: AnyPublisher<Void, Never> { get }
}

class MainTitleBarView: UIView, MainTitleBar {
    var view: UIView {
        self
    }
    
    var menuPublisher: AnyPublisher<Void, Never> {
        menu.publisher(for: .touchUpInside)
    }
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "TO-DO LIST"
        label.font = .systemFont(ofSize: 32)
        label.textColor = .black
        return label
    }()
    
    private let menu: UIButton = {
        var configuration = UIButton.Configuration.plain()
        configuration.contentInsets = .init(top: 11, leading: 11, bottom: 11, trailing: 11)

        let button = UIButton(configuration: configuration, primaryAction: nil)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setImage(UIImage(named: "ic_menu"), for: .normal)
        button.tintColor = .black
        return button
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        layout()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        layout()
    }
    
    private func layout() {
        addSubview(titleLabel)
        addSubview(menu)
        
        NSLayoutConstraint.activate([
            titleLabel.leftAnchor.constraint(equalTo: leftAnchor, constant: 48),
            titleLabel.centerYAnchor.constraint(equalTo: centerYAnchor),
            
            menu.rightAnchor.constraint(equalTo: rightAnchor, constant: -37),
            menu.centerYAnchor.constraint(equalTo: centerYAnchor),
            menu.widthAnchor.constraint(equalToConstant: 46),
            menu.heightAnchor.constraint(equalToConstant: 46)
        ])
    }
}
