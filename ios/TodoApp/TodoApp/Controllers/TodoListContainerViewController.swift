//
//  ViewController.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/04.
//

import UIKit

class TodoListContainerViewController: UIViewController {
    @IBOutlet weak var menuButton: UIButton!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var columnStack: UIStackView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.configureMenuButton()
    }
    
    private func configureMenuButton() {
        self.menuButton.addAction(UIAction(handler: self.toggleMenuButton(_:)), for: .touchUpInside)
    }
    
    private func toggleMenuButton(_ action: UIAction) {
        // TODO: 활동기록을 보여주는 ViewController 보여주기
    }


}

