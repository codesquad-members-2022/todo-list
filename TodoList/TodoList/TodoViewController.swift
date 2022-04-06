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
        self.view.backgroundColor = UIColor.gray
        setUIProperties(title: "해야 할 일")
    }

    func setUIProperties(title: String) {
        todoLabel.text = title
    }
}
