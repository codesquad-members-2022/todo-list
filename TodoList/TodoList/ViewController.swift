//
//  ViewController.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/05.
//

import UIKit

class ViewController: UIViewController {

    let factory = CardFactory()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print(factory.createRandomCard())
    }
}

