//
//  MainViewController.swift
//  TodoList
//
//  Created by seongha shin on 2022/04/04.
//

import Foundation
import UIKit
import Combine

class MainViewController: UIViewController {
    
    let titleBar: MainTitleBar = {
        let titleBarView = MainTitleBarView()
        titleBarView.translatesAutoresizingMaskIntoConstraints = false
        return titleBarView
    }()
    
    let columnTableViews: [CardsColumnView] = {
        return [
            ColumnViewController(status: .todo),
            ColumnViewController(status: .progress),
            ColumnViewController(status: .done)
        ]
    }()
    
    let columnStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.spacing = 22
        return stackView
    }()
    
    var cancellables = Set<AnyCancellable>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        attribute()
        layout()
    }
    
    private func attribute() {
        self.view.backgroundColor = .gray6
    }
    
    private func layout() {
        columnTableViews.forEach{ self.embed($0.controller) }
        let safeArea = self.view.safeAreaLayoutGuide

        self.view.addSubview(titleBar.view)
        titleBar.view.topAnchor.constraint(equalTo: safeArea.topAnchor).isActive = true
        titleBar.view.heightAnchor.constraint(equalToConstant: 72).isActive = true
        titleBar.view.leftAnchor.constraint(equalTo: safeArea.leftAnchor).isActive = true
        titleBar.view.rightAnchor.constraint(equalTo: safeArea.rightAnchor).isActive = true
        
        self.view.addSubview(columnStackView)
        columnStackView.topAnchor.constraint(equalTo: titleBar.view.bottomAnchor, constant: 51).isActive = true
        columnStackView.bottomAnchor.constraint(equalTo: safeArea.bottomAnchor).isActive = true
        columnStackView.leftAnchor.constraint(equalTo: safeArea.leftAnchor, constant: 48).isActive = true
        columnStackView.rightAnchor.constraint(equalTo: safeArea.rightAnchor, constant: -48).isActive = true
        
        columnTableViews.forEach {
            $0.controller.view.widthAnchor.constraint(equalToConstant: 256).isActive = true
            columnStackView.addArrangedSubview($0.controller.view)
        }
        columnStackView.addArrangedSubview(UIView())
    }
}
