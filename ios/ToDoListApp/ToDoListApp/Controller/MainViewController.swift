//
//  MainViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/05.
//

import UIKit

class MainViewController: UIViewController {
    
    private let kanbanViewControllers: [UIViewController] = {
        return [ToDoViewController(), InProgressViewController(), DoneViewController()]
    }()
    
    private let titleView = TitleView()
    
    private let tableStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 22
        stackView.distribution = .fillEqually
        return stackView
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpView()
    }
    
    private func setUpView() {
        configureView()
        
        setChildViewControllers()
        
        view.addSubview(titleView)
        view.addSubview(tableStackView)
        configureTableStackView()
        
        layoutTitleView()
        layoutTableStackView()
    }
    
    private func configureView() {
        view.backgroundColor = .systemGray6
    }
    
    private func setChildViewControllers() {
        kanbanViewControllers.forEach { kanbanViewController in
            addChildViewController(child: kanbanViewController, parent: self)
        }
    }
    
    private func addChildViewController(child: UIViewController, parent: UIViewController) {
        addChild(child)
        child.didMove(toParent: parent)
    }
    
    private func configureTableStackView() {
        kanbanViewControllers.forEach { kanbanViewController in
            tableStackView.addArrangedSubview(kanbanViewController.view)
        }
    }
    
    private func layoutTitleView() {
        titleView.translatesAutoresizingMaskIntoConstraints = false
        
        titleView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor).isActive = true
        titleView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        titleView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        titleView.heightAnchor.constraint(equalToConstant: 72).isActive = true
    }
    
    private func layoutTableStackView() {
        tableStackView.translatesAutoresizingMaskIntoConstraints = false
        
        tableStackView.topAnchor.constraint(equalTo: titleView.bottomAnchor, constant: 64).isActive = true
        tableStackView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -50).isActive = true
        tableStackView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 48).isActive = true
        tableStackView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -343).isActive = true
    }
}
