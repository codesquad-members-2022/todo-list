//
//  MainViewController.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import UIKit
import Combine

class MainViewController: UIViewController {
    
    private let titleBar: MainTitleBar = {
        let titleBarView = MainTitleBarView()
        titleBarView.translatesAutoresizingMaskIntoConstraints = false
        return titleBarView
    }()
    
    private let columnTableViews: [ColumnViewProperty&ColumnViewInput] = {
        return [
            ColumnViewController(model: ColumnViewModel(columnType: .todo)),
            ColumnViewController(model: ColumnViewModel(columnType: .progress)),
            ColumnViewController(model: ColumnViewModel(columnType: .done))
        ]
    }()
    
    private let logViewController: LogViewController = {
        let viewController = LogViewController()
        viewController.view.translatesAutoresizingMaskIntoConstraints = false
        viewController.view.isHidden = true
        return viewController
    }()
    
    private let columnStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.spacing = 22
        return stackView
    }()
    
    private var cancellables = Set<AnyCancellable>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bind()
        attribute()
        layout()
    }
    
    private func bind() {
        columnTableViews.forEach{ value in
            value.controller.delegate = self
        }
        
        titleBar.menuPublisher
            .sink {
                self.logViewController.view.isHidden = false
            }.store(in: &cancellables)
    }
    
    private func attribute() {
        self.view.backgroundColor = .gray6
    }
    
    private func layout() {
        let safeArea = view.safeAreaLayoutGuide

        embed(logViewController)
        
        view.addSubview(titleBar.view)
        view.addSubview(columnStackView)
        view.addSubview(logViewController.view)
        
        NSLayoutConstraint.activate([
            titleBar.view.topAnchor.constraint(equalTo: safeArea.topAnchor),
            titleBar.view.heightAnchor.constraint(equalToConstant: 72),
            titleBar.view.leftAnchor.constraint(equalTo: safeArea.leftAnchor),
            titleBar.view.rightAnchor.constraint(equalTo: safeArea.rightAnchor),
            
            columnStackView.topAnchor.constraint(equalTo: titleBar.view.bottomAnchor, constant: 51),
            columnStackView.bottomAnchor.constraint(equalTo: safeArea.bottomAnchor),
            columnStackView.leftAnchor.constraint(equalTo: safeArea.leftAnchor, constant: 48),
            columnStackView.rightAnchor.constraint(equalTo: safeArea.rightAnchor, constant: -48),
            
            logViewController.view.topAnchor.constraint(equalTo: self.view.topAnchor),
            logViewController.view.bottomAnchor.constraint(equalTo: self.view.bottomAnchor),
            logViewController.view.rightAnchor.constraint(equalTo: safeArea.rightAnchor),
            logViewController.view.widthAnchor.constraint(equalToConstant: 428)
        ])
        
        columnTableViews.forEach {
            self.embed($0.controller)
            $0.controller.view.widthAnchor.constraint(equalToConstant: 256).isActive = true
            columnStackView.addArrangedSubview($0.controller.view)
        }
        columnStackView.addArrangedSubview(UIView())
    }
}

extension MainViewController: ColumnViewDelegate {
    func columnView(_ columnView: ColumnViewController, fromCard: Card, toColumn: Card.Column) {
        columnTableViews[toColumn.index].addCard(fromCard)
    }
}
