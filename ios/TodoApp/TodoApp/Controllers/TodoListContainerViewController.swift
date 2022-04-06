//
//  ViewController.swift
//  TodoApp
//
//  Created by 송태환 on 2022/04/04.
//

import UIKit

class TodoListContainerViewController: UIViewController {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var menuButton: UIButton!
    @IBOutlet weak var drawerView: UIView!
    @IBOutlet weak var closeButton: UIButton!
    @IBOutlet weak var columnStack: UIStackView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.configureUI()
    }
    
    private func configureUI() {
        self.drawerView.frame.origin.x = self.view.frame.width
        self.menuButton.addAction(UIAction(handler: self.toggleMenuButton(_:)), for: .touchUpInside)
        self.closeButton.addAction(UIAction(handler: self.toggleMenuButton(_:)), for: .touchUpInside)
    }
    
    private func toggleMenuButton(_ action: UIAction) {
        UIView.animate(withDuration: 0.3, delay: 0, options: .curveEaseInOut) {
            if self.drawerView.frame.origin.x == self.view.frame.width {
                self.drawerView.frame.origin = CGPoint(x: self.drawerView.frame.origin.x - self.drawerView.frame.width, y: 0)
            } else {
                self.drawerView.frame.origin = CGPoint(x: self.drawerView.frame.origin.x + self.drawerView.frame.width, y: 0)
            }
        }
    }


}

