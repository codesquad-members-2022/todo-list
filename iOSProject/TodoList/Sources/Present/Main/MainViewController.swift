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
    
    private var columnTableViews: [Column.ColumnType:ColumnViewControllerProtocol] = [:]
    
    private var cancellables = Set<AnyCancellable>()
    private let model: MainViewModelProtocol = MainViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bind()
        attribute()
        layout()
        
        model.action.loadColumns.send()
    }
    
    private func bind() {
        model.state.loadedColumns
            .receive(on: DispatchQueue.main)
            .sink{ models in
                models.forEach { model in
                    let viewController = ColumnViewController(model: model)
                    viewController.delegate = self
                    viewController.view.widthAnchor.constraint(equalToConstant: 256).isActive = true
                    self.embed(viewController)
                    self.columnStackView.addArrangedSubview(viewController.view)
                    self.columnTableViews[model.columnType] = viewController
                }
                self.columnStackView.addArrangedSubview(UIView())
            }
            .store(in: &cancellables)
                
        titleBar.menuPublisher
            .sink {
                self.logViewController.view.isHidden = false
            }.store(in: &cancellables)
        
        model.state.movedCard
            .sink { card, from, to, row in
                guard let fromColumn = self.columnTableViews[from],
                      let toColumn = self.columnTableViews[to] else {
                    return
                }
                
                if from == to {
                    fromColumn.moveCard(card, toRow: row)
                } else {
                    fromColumn.deleteCard(card)
                    toColumn.addCard(card, at: row)
                }
            }
            .store(in: &cancellables)
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
    }
}

extension MainViewController: ColumnViewDelegate {
    func columnView(_ columnView: ColumnViewController, dragCard: DragCard, toColumn: Column.ColumnType, toRow: Int) {
        model.action.moveCard.send((dragCard, toColumn, toRow))
    }
    
    func columnView(_ columnView: ColumnViewController, fromCard: Card, toColumn: Column.ColumnType) {
        columnTableViews[toColumn]?.addCard(fromCard, at: 0)
    }
}
