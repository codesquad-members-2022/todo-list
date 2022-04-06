//
//  ViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/04.
//

import UIKit

class ContainerViewController: UIViewController {
    
    var headerVC: HeaderViewController!
    var contentVC: ContentViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .systemGray5
        addChildViewController()
    }
}

private extension ContainerViewController{
    func addChildViewController(){
        headerVC = HeaderViewController()
        self.addChild(headerVC)
        self.view.addSubview(headerVC.view)
        
        contentVC = ContentViewController()
        self.addChild(contentVC)
        self.view.addSubview(contentVC.view)
        
        configureChildViewLayout()
    }
    
    func configureChildViewLayout(){
        headerVC.view.translatesAutoresizingMaskIntoConstraints = false
        headerVC.view.leadingAnchor.constraint(equalTo: self.view.leadingAnchor).isActive = true
        headerVC.view.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        headerVC.view.widthAnchor.constraint(equalTo: self.view.widthAnchor).isActive = true
        headerVC.view.heightAnchor.constraint(equalToConstant: view.frame.height / 9).isActive = true
        
        contentVC.view.translatesAutoresizingMaskIntoConstraints = false
        contentVC.view.topAnchor.constraint(equalTo: headerVC.view.bottomAnchor).isActive = true
        contentVC.view.leadingAnchor.constraint(equalTo: self.view.leadingAnchor).isActive = true
        contentVC.view.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
        contentVC.view.widthAnchor.constraint(equalToConstant: self.view.frame.width * (5/7) ).isActive = true
    }
}

