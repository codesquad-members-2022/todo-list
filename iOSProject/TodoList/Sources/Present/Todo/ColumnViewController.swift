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
    
    private let count: UILabel = {
        let label = PaddingLabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "100"
        label.font = .systemFont(ofSize: 14, weight: .bold)
        label.textColor = .black
        label.backgroundColor = .gray4
        label.padding = .init(top: 8, left: 9, bottom: 8, right: 9)
        label.layer.masksToBounds = true
        label.layer.cornerRadius = 13
        return label
    }()
    
    private let cardTable: UITableView = {
        let tableView = UITableView()
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.register(ColumnViewCell.self, forCellReuseIdentifier: "ColumnViewCell")
        tableView.backgroundColor = .clear
        tableView.separatorStyle = .none
        return tableView
    }()
    
    private let add: UIButton = {
        var configuration = UIButton.Configuration.plain()
        configuration.contentInsets = .init(top: 16, leading: 16, bottom: 16, trailing: 16)

        let button = UIButton(configuration: configuration, primaryAction: nil)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setImage(UIImage(named: "ic_add"), for: .normal)
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
        cardTable.delegate = self
        cardTable.dataSource = self
    }
    
    private func attribute() {
    }
    
    private func layout() {
        self.view.addSubview(titleLabel)
        titleLabel.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        titleLabel.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 8).isActive = true
        
        self.view.addSubview(count)
        count.leftAnchor.constraint(equalTo: titleLabel.rightAnchor, constant: 8).isActive = true
        count.centerYAnchor.constraint(equalTo: titleLabel.centerYAnchor).isActive = true
        count.heightAnchor.constraint(equalToConstant: 26).isActive = true
        
        self.view.addSubview(add)
        add.centerYAnchor.constraint(equalTo: titleLabel.centerYAnchor).isActive = true
        add.rightAnchor.constraint(equalTo: self.view.rightAnchor, constant: -8).isActive = true
        add.widthAnchor.constraint(equalToConstant: 24).isActive = true
        add.heightAnchor.constraint(equalToConstant: 24).isActive = true
        
        self.view.addSubview(cardTable)
        cardTable.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 16).isActive = true
        cardTable.leftAnchor.constraint(equalTo: self.view.leftAnchor).isActive = true
        cardTable.rightAnchor.constraint(equalTo: self.view.rightAnchor).isActive = true
        cardTable.bottomAnchor.constraint(equalTo: self.view.bottomAnchor, constant:  -10).isActive = true
    }
}

extension ColumnViewController: UITableViewDelegate {
    
}
extension ColumnViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        3
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "ColumnViewCell") as? ColumnViewCell else {
            return UITableViewCell()
        }
        
        return cell
    }
}
