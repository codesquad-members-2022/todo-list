//
//  NewCardViewController.swift
//  TodoList
//
//  Created by Bibi on 2022/04/12.
//

import UIKit

class NewCardViewController: UIViewController {

    private let newCardView = NewCardView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view = newCardView
        newCardView.setUIConstraint()
    }
}
