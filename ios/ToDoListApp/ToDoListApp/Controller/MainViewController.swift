//
//  MainViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/05.
//

import UIKit

class MainViewController: UIViewController {
    
    private(set) var kanbanColumnViewControllers: [KanbanColumnViewController] = {
        return KanbanType.allCases.map{ KanbanColumnViewController(type: $0) }
    }()
    
    private let titleView = TitleView()
    
    private let tableStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 22
        stackView.distribution = .fillEqually
        return stackView
    }()
    
    var delegate: MainViewControllerDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpView()
        addTargetActions()
    }
    
    private func setUpView() {
        configureView()
        
        setChildViewControllers()
        
        view.addSubview(titleView)
        layoutTitleView()
        
        view.addSubview(tableStackView)
        configureTableStackView()
        layoutTableStackView()
    }
    
    
    private func configureView() {
        view.backgroundColor = .systemGray6
    }
    
    private func setChildViewControllers() {
        kanbanColumnViewControllers.forEach { kanbanColumnViewController in
            addChildViewController(child: kanbanColumnViewController, parent: self)
        }
    }
    
    private func configureTableStackView() {
        kanbanColumnViewControllers.forEach { kanbanColumnViewController in
            tableStackView.addArrangedSubview(kanbanColumnViewController.view)
        }
    }
    
    private func addChildViewController(child: UIViewController, parent: UIViewController) {
        addChild(child)
        child.didMove(toParent: parent)
    }
    
    private func addTargetActions() {
        titleView.inspectorButton.addTarget(self,
                                            action: #selector(didTapInspectorOpenButton),
                                            for: .touchUpInside)
    }
}

//MARK: - View Layouts

extension MainViewController {
    
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
        tableStackView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor).isActive = true
        tableStackView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 48).isActive = true
        tableStackView.widthAnchor.constraint(equalTo: view.widthAnchor, multiplier: 0.72).isActive = true
    }
}

//MARK: - Selector Functions

extension MainViewController {
    @objc func didTapInspectorOpenButton() {
        delegate?.didTapInspectorOpenButton()
    }
}
