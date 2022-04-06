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
        self.drawerView.frame.origin.x = self.view.frame.maxX
        self.menuButton.addAction(UIAction(handler: self.toggleMenuButton(_:)), for: .touchUpInside)
        self.closeButton.addAction(UIAction(handler: self.toggleMenuButton(_:)), for: .touchUpInside)
    }
    
    
    private func toggleMenuButton(_ action: UIAction) {
        let x = self.drawerView.frame.origin.x
        let width = self.drawerView.frame.width
        var point = CGPoint(x: self.drawerView.frame.origin.x, y: 0)
        
        UIView.animate(withDuration: 0.3, delay: 0, options: .curveEaseInOut) { [weak self] in
            
            if x == self?.view.frame.width {
                point.x = x - width
            } else {
                point.x = x + width
            }

            self?.drawerView.frame.origin = point
        }
    }


}

