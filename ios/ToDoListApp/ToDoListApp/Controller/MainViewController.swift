//
//  MainViewController.swift
//  ToDoListApp
//
//  Created by 김상혁 on 2022/04/05.
//

import UIKit

class MainViewController: UIViewController {
    
    private let kanbanColumnViewControllers: [KanbanColumnViewController] = {
        return [KanbanColumnViewController(type: .toDo),
                KanbanColumnViewController(type: .inProgress),
                KanbanColumnViewController(type: .done)]
    }()
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.text = "TO-DO LIST"
        label.font = UIFont(name: "Apple SD Gothic Neo Bold", size: 32)
        return label
    }()
    
    private let tableStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 22
        stackView.distribution = .fillEqually
        return stackView
    }()
    
    weak var delegate: MainViewControllerDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpView()
        setNavigationBarButtonItems()
    }
    
    private func setUpView() {
        configureView()
        
        setChildViewControllers()
        
        view.addSubview(tableStackView)
        configureTableStackView()
        layoutTableStackView()
    }
    
    private func setNavigationBarButtonItems() {
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: titleLabel)
        navigationItem.rightBarButtonItem = UIBarButtonItem(image: UIImage(systemName: "line.3.horizontal"),
                                                            style: .done,
                                                            target: self,
                                                            action: #selector(didTapInspectorButton))
    }
    
    private func configureView() {
        view.backgroundColor = .systemGray6
    }
    
    private func setChildViewControllers() {
        kanbanColumnViewControllers.forEach { kanbanColumnViewController in
            addChildViewController(child: kanbanColumnViewController, parent: self)
        }
    }
    
    private func addChildViewController(child: UIViewController, parent: UIViewController) {
        addChild(child)
        child.didMove(toParent: parent)
    }
    
    private func configureTableStackView() {
        kanbanColumnViewControllers.forEach { kanbanColumnViewController in
            tableStackView.addArrangedSubview(kanbanColumnViewController.view)
        }
    }
    
    private func layoutTableStackView() {
        tableStackView.translatesAutoresizingMaskIntoConstraints = false
        
        tableStackView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 64).isActive = true
        tableStackView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -50).isActive = true
        tableStackView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 48).isActive = true
        tableStackView.widthAnchor.constraint(equalTo: view.widthAnchor, multiplier: 0.7).isActive = true
    }
}

//MARK: - selector functions

extension MainViewController {
    @objc func didTapInspectorButton() {
        delegate?.didTapInspectorButton()
    }
}
