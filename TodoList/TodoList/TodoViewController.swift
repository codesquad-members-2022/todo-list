//
//  TodoViewController.swift
//  TodoList
//
//  Created by Bibi on 2022/04/06.
//

import UIKit

class TodoViewController: UIViewController {

    @IBOutlet weak var todoLabel: UILabel!
    @IBOutlet weak var todoCountLabel: UILabel!
    @IBOutlet weak var addCardButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    func setUIProperties(title: String) {
        todoLabel.text = title
    }
}
