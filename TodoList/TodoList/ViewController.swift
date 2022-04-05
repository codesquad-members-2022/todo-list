//
//  ViewController.swift
//  TodoList
//
//  Created by 최예주 on 2022/04/04.
//

import UIKit

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        addChildViewController()
    }
    
    private func addChildViewController(){
        let headerVC = HeaderViewController()
        self.addChild(headerVC)
        self.view.addSubview(headerVC.view)
    }
}

