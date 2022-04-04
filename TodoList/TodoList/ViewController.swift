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
        
        setHeaderView()
    }
    
    func setHeaderView(){
        let header = HeaderView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height / 6))
        self.view.addSubview(header)
    }
}

