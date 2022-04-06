//
//  MainViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/05.
//

import UIKit

class MainViewController: UIViewController {
    
    private let titleView = TitleView()
    private let toDoViewController = ToDoViewController()
    private let inProgressViewController = InProgressViewController()
    private let doneViewController = DoneViewController()
    
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
        addChildViewController(child: toDoViewController, parent: self)
        addChildViewController(child: inProgressViewController, parent: self)
        addChildViewController(child: doneViewController, parent: self)
    }
    
    private func addChildViewController(child: UIViewController, parent: UIViewController) {
        addChild(child)
        child.didMove(toParent: parent)
    }
    
    private func layoutTitleView() {
        titleView.translatesAutoresizingMaskIntoConstraints = false
        
        titleView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor).isActive = true
        titleView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        titleView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        titleView.heightAnchor.constraint(equalToConstant: 72).isActive = true
    }
    
    private func configureTableStackView() {
        guard let toDoView = toDoViewController.view else { return }
        guard let inProgressView = inProgressViewController.view else { return }
        guard let doneView = doneViewController.view else { return }
        
        tableStackView.addArrangedSubview(toDoView)
        tableStackView.addArrangedSubview(inProgressView)
        tableStackView.addArrangedSubview(doneView)
    }
    
    private func layoutTableStackView() {
        tableStackView.translatesAutoresizingMaskIntoConstraints = false
        
        tableStackView.topAnchor.constraint(equalTo: titleView.bottomAnchor, constant: 64).isActive = true
        tableStackView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -50).isActive = true
        tableStackView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 48).isActive = true
        tableStackView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -343).isActive = true
    }
}
