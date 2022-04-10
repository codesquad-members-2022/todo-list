//
//  InspectorViewController.swift
//  ToDoListApp
//
//  Created by Jihee hwang on 2022/04/07.
//

import UIKit

class InspectorViewController: UIViewController {
    
    private let tableView = UITableView(frame: .zero, style: .plain)
    private let tableViewDataSource = InspectorTableViewDataSource()
    
    private let closeButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: Constant.ImageName.xmark), for: .normal)
        button.tintColor = .black
        return button
    }()
    
    var delegate: InspectorViewControllerDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpView()
        addTargetActions()
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
        tableView.register(InspectorTableViewCell.self, forCellReuseIdentifier: InspectorTableViewCell.indentifier)
        tableView.dataSource = tableViewDataSource
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
        closeButton.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -54).isActive = true
    }
    
    private func addTargetActions() {
        closeButton.addTarget(self,
                              action: #selector(didTapInspectorCloseButton),
                              for: .touchUpInside)
    }
}

//MARK: - selector functions

extension InspectorViewController {
    @objc func didTapInspectorCloseButton() {
        delegate?.didTapInspectorCloseButton()
    }
}
