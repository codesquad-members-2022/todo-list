//
//  ViewController.swift
//  TodoList
//
//  Created by 김동준 on 2022/04/05.
//

import UIKit

class CardBoardViewController: UIViewController {

    let factory = CardFactory()
    let cardBoard = CardBoard()
    
    @IBOutlet weak var boardStackView: UIStackView!
    @IBOutlet weak var todoContainerView: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let todoStoryBoard = UIStoryboard(name: "TodoStoryboard", bundle: Bundle(for: TodoViewController.self))
        guard let todoViewController = todoStoryBoard.instantiateViewController(withIdentifier: "TodoViewController") as? TodoViewController else {
            return
        }
        addChild(todoViewController)
        todoContainerView.addSubview(todoViewController.view)
        todoViewController.view.frame = todoContainerView.frame
    }
    
    
}

