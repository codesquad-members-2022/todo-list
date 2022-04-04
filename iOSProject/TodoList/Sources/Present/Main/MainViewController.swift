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
    
    var cancellables = Set<AnyCancellable>()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bind()
        attribute()
        layout()
    }
    
    private func bind() {
        titleBar.menuPublisher
            .sink {
                
            }.store(in: &cancellables)
    }
    
    private func attribute() {
        self.view.backgroundColor = .white
    }
    
    private func layout() {
        let safeArea = self.view.safeAreaLayoutGuide
        
        self.view.addSubview(titleBar.view)
        titleBar.view.leftAnchor.constraint(equalTo: safeArea.leftAnchor).isActive = true
        titleBar.view.rightAnchor.constraint(equalTo: safeArea.rightAnchor).isActive = true
        titleBar.view.topAnchor.constraint(equalTo: safeArea.topAnchor).isActive = true
        titleBar.view.heightAnchor.constraint(equalToConstant: 72).isActive = true
    }
}
