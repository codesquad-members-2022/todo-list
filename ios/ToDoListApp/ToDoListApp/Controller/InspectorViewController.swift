//
//  InspectorViewController.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/07.
//

import UIKit

class InspectorViewController: UIViewController {

    private let tableView = UITableView(frame: .zero, style: .plain)
    private let taskTableViewDataSource = TaskTableViewDataSource()
    
    private let closeButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: "xmark"), for: .normal)
        button.tintColor = .black
        return button
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpView()
    }
    
    private func setUpView() {
        configureView()
        
        view.addSubview(tableView)
        view.addSubview(closeButton)
        
        configureCustomTableView()
        layoutTableView()
        layoutCloseButton()
    }
    
    private func configureView() {
        view.backgroundColor = .white
        let lineColor = UIColor(red: 235 / 255, green: 235 / 255, blue: 235 / 255, alpha: 1)
        view.layer.addBorder([.left], color: lineColor, width: 1)
    }
    
    private func configureCustomTableView() {
        tableView.register(InspectorViewCell.self, forCellReuseIdentifier: InspectorViewCell.indentifier)
        tableView.dataSource = taskTableViewDataSource
        tableView.separatorStyle = .none
        tableView.backgroundColor = .clear
    }
    
    private func layoutTableView() {
        tableView.translatesAutoresizingMaskIntoConstraints = false
        
        tableView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 80).isActive = true
        tableView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor).isActive = true
        tableView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 48).isActive = true
        tableView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -48).isActive = true
    }
    
    private func layoutCloseButton() {
        closeButton.translatesAutoresizingMaskIntoConstraints = false
        
        closeButton.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 30).isActive = true
        closeButton.bottomAnchor.constraint(equalTo: tableView.topAnchor, constant: -39).isActive = true
        closeButton.centerXAnchor.constraint(equalTo: tableView.centerXAnchor, constant: 170).isActive = true
        closeButton.widthAnchor.constraint(equalToConstant: 30).isActive = true
    }
}
